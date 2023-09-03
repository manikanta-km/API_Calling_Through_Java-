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
        String myUrl =  "https://api.nationalize.io/?name=nathaniel";



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

                System.out.println(jsonAPIResponse.get("count"));
                System.out.println(jsonAPIResponse.get("name"));

                JSONArray countryArray = jsonAPIResponse.getJSONArray("country");
                for (int i = 0; i < countryArray.length(); i++) {
                    JSONObject countryInfo = countryArray.getJSONObject(i);
                    System.out.println("Country ID: " + countryInfo.getString("country_id"));
                    System.out.println("Probability: " + countryInfo.getDouble("probability"));
                }

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