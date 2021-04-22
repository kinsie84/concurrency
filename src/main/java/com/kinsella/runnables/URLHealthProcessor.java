package com.kinsella.runnables;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLHealthProcessor implements Runnable {

    private static final String URL_NAME = "https://www.daft.ie/";

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : checking the health of URL");
        String appStatus = "";
        try {
            URL url = new URL(URL_NAME);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                appStatus = "working";
            } else {
                appStatus = "not working";
            }
        } catch (IOException e) {
            Logger.getLogger(URLHealthProcessor.class.getName()).log(Level.SEVERE,null, e);
        }
        System.out.print(appStatus);

    }
}
