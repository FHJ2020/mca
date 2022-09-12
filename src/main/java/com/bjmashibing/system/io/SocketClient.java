package com.bjmashibing.system.io;

import thread.SleepHelper;

import java.io.*;
import java.net.Socket;

/**
 * @author: 马士兵教育
 * @create: 2020-05-17 16:18
 */
public class SocketClient {

    public static void main(String[] args) {

        try {
            Socket client = new Socket("192.168.2.128", 9090);

            client.setSendBufferSize(25);
            //true 关闭 nlg, false 开启
            client.setTcpNoDelay(false);

//            client.setOOBInline(false);

//            client.setSoTimeout(3000);
            OutputStream out = client.getOutputStream();


            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    byte[] bb = line.getBytes();
                    System.out.println("byte len:  " + bb.length + " , str len: " + line.length());
                    SleepHelper.sleepMicroSecond(1);
                    for (byte b : bb) {
                        out.write(b);
                    }

                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
