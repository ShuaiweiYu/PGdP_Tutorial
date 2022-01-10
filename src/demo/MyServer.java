package demo;

import java.io.*;
import java.net.*;
import java.util.Date;

//IP地址（英语：IP Address，全称Internet Protocol Address），
// 又译为网际协议地址、互联网协议地址。是网际协议中用于标识发送或接收数据报的设备的一串数字

//IP地址有两个主要的功能：
//  标识主机：更具体地说，标识其网络接口，并且提供主机在网络中的位置。
//  网络寻址：网际协议（缩写：IP）的一个重要机制就是网络寻址（英语：internet address）。
//  该功能的目的是将数据报从一个网络模块送到目的地。在发送的整个过程，IP地址（address）充当着目的地的位置，
//  域名（name）意味着我们要找什么，路由（route）代表着如何到达目的地的这个过程。

//host -> 主机名

//port(端口号) -> 计算机上在运行的进程（程序），用来识别电脑上哪个程序在运行

//--> IP + port = socket


public class MyServer {
    public static void main(String[] args) {

        try (//创建服务器端socket，指明自己的端口号
             ServerSocket serverSocket = new ServerSocket(8888);
             //使用accept来获得来自于客户端的socket
             Socket socket = serverSocket.accept();
             //获得输入流
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             //获得输出流
             PrintWriter writer = new PrintWriter(socket.getOutputStream())
             ) {

            while (true) {
                String message = in.readLine();
                System.out.println("Message received at " + new Date() + "\nContent: \"" + message + "\"");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
