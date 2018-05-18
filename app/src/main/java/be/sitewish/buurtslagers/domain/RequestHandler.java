package be.sitewish.buurtslagers.domain;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    public String sendPostRequest(String requestURL, HashMap<String,String> params){
        URL url;

        StringBuilder sb = new StringBuilder();
        try{
            url = new URL(requestURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(1500);
            connection.setConnectTimeout(1500);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(getPostDataString(params));

            System.out.println("URL " + getPostDataString(params));
            writer.flush();
            writer.close();
            os.close();

            int repsonseCode = connection.getResponseCode();
            if(repsonseCode == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                sb = new StringBuilder();

                String response = "";
                while ((response = br.readLine()) != null) {
                    sb.append(response);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR");
        }

        return sb.toString();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
