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
        String myUrl =  "https://api.zippopotam.us/us/33162";

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

                System.out.println("Post Code : "+jsonAPIResponse.get("post code"));
                System.out.println("Country : "+jsonAPIResponse.get("country"));
                System.out.println("Country Abbreviation : "+jsonAPIResponse.get("country abbreviation"));

                JSONArray countryArray = jsonAPIResponse.getJSONArray("places");
                for (int i = 0; i < countryArray.length(); i++) {
                    JSONObject countryInfo = countryArray.getJSONObject(i);
                    System.out.println("Place Name: " + countryInfo.getString("place name"));
                    System.out.println("Longitude: " + countryInfo.getDouble("longitude"));
                    System.out.println("State: " + countryInfo.getString("state"));
                    System.out.println("State Abb: " + countryInfo.getString("state abbreviation"));
                    System.out.println("Latitude: " + countryInfo.getString("latitude"));
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