package com.geekbrains.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    private static Socket socket;
    private static Scanner in;
    private static PrintWriter out;
    private static Scanner sc;

    private static BufferedInputStream bis;
    private static BufferedOutputStream bos;
    private static byte[] byteArray;
    private static int ins;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8199);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            sc = new Scanner(System.in);
            String str;
            while (!(str = sc.nextLine()).equals("/end")) {
                File file = new File(str);
                if (file.exists()) {
                    System.out.println("exists");
                    System.out.println(file.getAbsolutePath());

                    bis = new BufferedInputStream(new FileInputStream(file));
                    bos = new BufferedOutputStream(socket.getOutputStream());
                    byteArray = new byte[8192];
                    while ((ins = bis.read(byteArray)) != -1){
                        bos.write(byteArray,0,ins);
                    }
                }
//                out.println(str);
//                out.flush();
//                String str1 = in.nextLine();
//                System.out.println("Сообщение от сервера: " + str1);
                bis.close();
                bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
}
