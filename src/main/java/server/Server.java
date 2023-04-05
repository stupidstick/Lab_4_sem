package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static List<MonoThreadClientHandler> clients = new ArrayList<>();
    public static List<Integer> idList = new ArrayList<>();
    private static Integer count = 0;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3345);
            while (true) {
                Socket client = server.accept();
                idList.add(count);
                System.out.println("id list: " + idList);
                System.out.println("client accept");
                try {
                        MonoThreadClientHandler clientThread = new MonoThreadClientHandler(client, count);
                        clients.add(clientThread);
                        clientThread.start();
                        clients.forEach(MonoThreadClientHandler::sendIdList);
                }
                catch (Exception exception){
                    client.close();
                }
                count++;
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}