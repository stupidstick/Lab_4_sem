package client;

import controller.View;
import data.Parameters;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client extends Thread{
    private int id;
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private ObservableList<String> clientsId;

    public Client(ObservableList<String> clientsId) {
        try {
            this.clientsId = clientsId;
            socket = new Socket("localhost", 3345);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Client connected to socket");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try  {
            //View.updateListId();
            id = in.read();
            while (!socket.isClosed()){
                String message = in.readLine();
                System.out.println(message);
                if (message.contains("giveParameters")){
                    out.write("parameters:" + message.split(":")[1] + ":" + Parameters.parametersToString() + "\n");
                    System.out.println("bebra");
                    out.flush();
                }
                else if (message.contains("id")){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                clientsId.setAll(message.split(":")[1].split(" "));
                            }
                            catch (Exception exception) {
                                clientsId.clear();
                            }
                        }
                    });

                }
                else if (message.contains("parameters")) {
                    Parameters.setParametersFromString(message.split(":")[1]);
                }
            }

        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public void parameterRequest(int id){
        try {
            out.write("getParameters:" + String.valueOf(id) + "\n");
            out.flush();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public void closeClient(){
        try {
            out.write("close\n");
            out.flush();
            socket.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
