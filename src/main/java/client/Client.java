package client;

import data.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client extends Thread{
    private int id;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ObservableList<String> clientsId;

    public Client(ObservableList<String> clientsId) {
        this.clientsId = clientsId;
    }
    public boolean connect(String host, int port) {
        try{
            socket = new Socket(host, port);
            System.out.println("client connected to socket");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            start();
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    @Override
    public void run(){
        try{
            while(!socket.isClosed()){
                Object message = in.readObject();
                if (message instanceof IdDTO){
                    id = ((IdDTO) message).getId();
                }
                if (message instanceof IdListDTO){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                clientsId.setAll(((IdListDTO) message).getIdList().stream().map(String::valueOf).toList());
                            }
                            catch (Exception exception){
                                clientsId.clear();
                            }
                        }
                    });
                }
                else if (message instanceof ParametersDTO){
                    Parameters.setParametersFromObject((ParametersDTO) message);
                }
                else if (message instanceof CommandDTO){
                    commandDispatch((CommandDTO) message);
                }
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void commandDispatch(CommandDTO command){
        if (command.getCommand().equals("giveParameters")){
            CommandDTO parametersCommand = new CommandDTO("parameters");

            parametersCommand.setValue(
                    new HashMap<String, Object>(Map.of(
                            "id", (Integer) command.getValue(),
                            "parameters", new ParametersDTO()
                    ))
            );

            try{
                out.writeObject(parametersCommand);
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    public void parameterRequest(int id){
        try {
            CommandDTO command = new CommandDTO("getParameters");
            command.setValue(id);
            out.writeObject(command);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public void closeClient(){
        try {
            out.writeObject(new CommandDTO("close"));
            socket.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
