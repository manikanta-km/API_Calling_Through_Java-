package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        URL  url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;
        String myUrl =  "https://api.chucknorris.io/jokes/random";

        try {
            url = new URL(myUrl);
        } catch (MalformedURLException e) {
            System.out.println("Something is wrong with the URL");
        }

        //Connection :

        try {
            connection = (HttpURLConnection)url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(responseCode==200)
        {

            try {
                BufferedReader in =  new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder apiData = new StringBuilder();
                String readLine = null;
                while((readLine = in.readLine())!=null){
                    apiData.append(readLine);
                }
                try{
                    in.close();
                }
                catch(IOException e){
                    throw new RuntimeException(e);
                }
                System.out.println(apiData.toString());
                JSONObject jsonAPIResponse = new JSONObject(apiData.toString());

                System.out.println("Categories : "+jsonAPIResponse.get("categories"));
                System.out.println("Created_At : "+jsonAPIResponse.get("created_at"));
                System.out.println("Icon_URL : "+jsonAPIResponse.get("icon_url"));
                System.out.println("ID : "+jsonAPIResponse.get("id"));
                System.out.println("Updated_At : "+jsonAPIResponse.get("updated_at"));
                System.out.println("URL : "+jsonAPIResponse.get("url"));
                System.out.println("Value : "+jsonAPIResponse.get("value"));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else
        {
            System.out.println("api call not successful!!");
        }

    }
}