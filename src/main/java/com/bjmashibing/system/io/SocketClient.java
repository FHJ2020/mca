package com.bjmashibing.system.io;

import java.io.*;
import java.net.Socket;

/**
 * @author: 马士兵教育
 * @create: 2020-05-17 16:18
 */
public class SocketClient {

    public static void main(String[] args) {

        try {
            Socket client = new Socket("192.168.2.128",9090);

            client.setSendBufferSize(20);
            client.setTcpNoDelay(true);
//            client.setSoTimeout(3000);
            OutputStream out = client.getOutputStream();
            BufferedReader readerS = new BufferedReader(new InputStreamReader(client.getInputStream()));


            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while(true){
                String line = reader.readLine();
                if(line != null ){
                    if ("1".equals(line)){
                        byte[] bb = line.getBytes();
                        for (byte b : bb) {
                            out.write(b);
                        }
                    }else{
                        System.out.println(readerS.readLine());
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
