package server;

import data.CommandDTO;
import data.IdDTO;
import data.IdListDTO;
import data.ParametersDTO;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MonoThreadClientHandler extends Thread{
    private Socket clientDialog;
    private Integer id;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public MonoThreadClientHandler(Socket client, int id) {
        this.id = id;
        clientDialog = client;
        try{
            in = new ObjectInputStream(clientDialog.getInputStream());
            out = new ObjectOutputStream(clientDialog.getOutputStream());
            sendIdList();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public void sendIdList(){
        try {
            IdListDTO list = new IdListDTO(Server.idList.stream().filter(id -> id != this.id).toList());
            out.writeObject(list);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void getParameters(int id){
        try{
            for (MonoThreadClientHandler client: Server.clients){
                if (client.id == id){
                    CommandDTO command = new CommandDTO("giveParameters");
                    command.setValue(this.id);
                    client.out.writeObject(command);
                }
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void sendParameters(int id, ParametersDTO parameters) {
            try {
                for (MonoThreadClientHandler client : Server.clients) {
                    if (client.id == id) {
                        client.out.writeObject(parameters);
                    }
                }
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
    }
    @Override
    public void run(){
        try {
            out.writeObject(new IdDTO(id));
            while (!clientDialog.isClosed()) {
                Object message = in.readObject();
                if (message instanceof CommandDTO){
                    CommandDTO command = (CommandDTO) message;

                    if (command.getCommand().equals("close")){
                        Server.idList.remove(id);
                        Server.clients.forEach(MonoThreadClientHandler::sendIdList);
                        Server.clients.remove(this);
                        clientDialog.close();
                    }

                    else if (command.getCommand().equals("getParameters")){
                        getParameters((Integer) command.getValue());
                    }
                    else if (command.getCommand().equals("parameters")){
                        Map<String, Object> parametersMap = (Map) command.getValue();
                        sendParameters((Integer) parametersMap.get("id"), (ParametersDTO) parametersMap.get("parameters"));
                    }
                }
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public int getClientId(){
        return id;
    }
}
