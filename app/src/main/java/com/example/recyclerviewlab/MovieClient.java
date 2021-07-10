package com.example.recyclerviewlab;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieClient {
    public static void getRequest(String url, JsonHttpResponseHandler handler) {
        new MovieClientTask(url, handler).execute();
    }

    public interface JsonHttpResponseHandler {
        void onResponseSuccess(int statusCode, JSONObject response);
    }

    public static class MovieClientTask extends AsyncTask<String, Void, JSONObject> {
        String url;
        JsonHttpResponseHandler handler;

        public MovieClientTask(String url, JsonHttpResponseHandler handler) {
            this.url = url;
            this.handler = handler;
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            URL url = null;
            InputStream stream = null;

            try {
                Log.d("MyApp", this.url);
                url = new URL(this.url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                stream = conn.getInputStream();

                return readJsonFromStream(stream);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private JSONObject readJsonFromStream(InputStream stream) throws JSONException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder jsonString = new StringBuilder();
            String line = "";
            try {
                while ((line = bufferedReader.readLine()) != null) {
//                    Log.d("MyApp", line);
                    jsonString.append(line);
                }
                stream.close();

                return new JSONObject(jsonString.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (handler != null) {
                handler.onResponseSuccess(200, jsonObject);
            }
        }
    }
}
