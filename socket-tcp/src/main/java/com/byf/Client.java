package com.byf;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
        System.out.println("已发起服务器连接，并进入后续流程！");
        System.out.println("客户端信息：" + socket.getLocalAddress() + " P: " + socket.getLocalPort());
        System.out.println("服务端信息：" + socket.getInetAddress() + " P: " + socket.getPort());

        try{
            todo(socket);
        } catch (Exception e){
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("客户端已退出~");

    }

    private static void todo(Socket socket) throws IOException {
        InputStream in = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        OutputStream outputStream = socket.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);

        InputStream inputStream = socket.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;
        do {
            // 键盘打印一行
            String str = br.readLine();
            // 发送到服务器
            socketPrintStream.println(str);

            // 从服务器读取一行
            String echo = socketBufferedReader.readLine();
            if("bye".equals(echo)){
                flag = false;
            } else {
                System.out.println(echo);
            }
        } while (flag);

    }
}
