package com.umutcanneset.demodatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;

    BackgroundTask(Context ctx){
        this.ctx=ctx;
    }



    protected String doInBackground(String... params) {


        String reg_url= "http://172.27.101.29/Project/register.php";
        String method= params[0];
      if(method.equals("register")){

          String name=params[1];
          String password=params[2];
          String contact=params[3];
          String country=params[4];

          try {
              URL url=new URL(reg_url);
              HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
              httpURLConnection.setRequestMethod("POST");
              httpURLConnection.setDoOutput(true);
              OutputStream os=httpURLConnection.getOutputStream();
              BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

              String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                      URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                      URLEncoder.encode("contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"+
                      URLEncoder.encode("country","UTF-8")+"="+URLEncoder.encode(country,"UTF-8");

              bufferedWriter.write(data);
              bufferedWriter.flush();
              bufferedWriter.close();
              os.close();
              InputStream IS=httpURLConnection.getInputStream();
              IS.close();
              return "Registration succesful";


          } catch (MalformedURLException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }


        return "nothing";
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
