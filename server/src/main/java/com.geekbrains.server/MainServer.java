package com.geekbrains.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class MainServer {
    private Vector<ClientHandler> clients;

    private static BufferedInputStream bis;
    private static BufferedOutputStream bos;
    private static byte[] byteArray;
    private static int ins;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8199);
            System.out.println("Ждвем подключения клиента");

            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            bis = new BufferedInputStream(socket.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream("text1.txt"));
 //           while (true) {
//                String str = in.nextLine();

                byteArray = new byte[8192];
                while ((ins = bis.read(byteArray)) != -1){
                    bos.write(byteArray,0,ins);
                }

 //               System.out.println("Сообщение от клиента: " + str);
 //               out.println("ECHO " + str);
                bos.flush();
            bis.close();
            bos.close();
       //     }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

