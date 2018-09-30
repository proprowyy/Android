package com.example.android.camera2basic;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
public class HttpSendData
{
    private static final String TAG ="HttpSendData" ;
    private  String Url="http://172.100.10.25:8080/nihao/Upload";
    private  String boundary = "-----------------------------7e2d1265096e";
    private  String  preFix = ("\r\n--"+ boundary+"\r\n");;
    private  String  pre = ("\r\n--"+ boundary+"--\r\n");;
    private URL httpurl=null;
    private DataOutputStream out;
    private HttpURLConnection conn;
    public HttpSendData( )
    {
        try {
            httpurl = new URL(Url);
            conn= (HttpURLConnection) httpurl.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false); //不允许使用缓存
            conn.setRequestProperty("Accept","*/*");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + boundary);
            out= new DataOutputStream(conn.getOutputStream());
        }catch ( IOException e) {
            e.printStackTrace();
            this.httpurl=null;
        }

    }

    public void SendJpg(byte[] Data)
    {
        try {

            out.writeBytes(preFix);
            out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + "pic.jpg" + "\"\r\n" );
            out.writeBytes("Content-Type: application/json; charset=utf-8" + "/r/n");
            out.writeBytes("Content-Type:" +"image/jpg" + "\r\n\r\n");
            out.write(Data,0,Data.length);
            out.writeBytes("\r\n");
            out.writeBytes(pre);
            out.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while((str = reader.readLine()) != null){
                sb.append(str);
            }

            Log.i(TAG, "run: \"reponse\"" + sb.toString());
            if (out != null){
                out.close();
            }
            if(reader != null){
                reader.close();
            }

        }catch (MalformedURLException  e){
             e.printStackTrace();
      }catch (IOException e){
            e.printStackTrace();
        }
    }
}
