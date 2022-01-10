package demo;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) {
        InetAddress ip = null;

        try {
            ip = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try (//创建socket对象，指明服务器端的IP和端口号
             Socket socket = new Socket(ip, 8888);
             //获取一个输出流，用于输出数据
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             //获得输入流
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             ) {

            while (true) {
                Scanner keyboard = new Scanner(System.in);
                String message = keyboard.nextLine();

                System.out.println("Message sent at " + new Date() + "\nContent: \"" + message + "\"");
                //写出数据
                writer.println(message);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
