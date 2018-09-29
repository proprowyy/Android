package com.example.android.camera2basic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class NetCmdServer {

    private  byte[] Buffer_Receive=null;
    private int prot=8554;
    private DatagramSocket msocketServer;
    private DatagramPacket mPacket;
    public void setNetworkPort(int nPort) {
        prot = nPort;
    }
    private static final String TAG = "NetCmdServer";
    public void initSocket()
    {
        try {

            Buffer_Receive= new byte[1024];
            mPacket=new DatagramPacket(Buffer_Receive,Buffer_Receive.length);
            msocketServer=new DatagramSocket(prot);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public String recSocket( ) {

        try {
            msocketServer.receive(mPacket);
            return new String(mPacket.getData(),0,mPacket.getLength());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
