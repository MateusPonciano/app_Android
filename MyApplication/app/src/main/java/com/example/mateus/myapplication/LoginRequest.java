package com.example.mateus.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    /*URLConnection connection = null;
    BufferedReader reader = null;
    try{
        URL url = new URL("localhost/accounts/login");
        connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        InputStream stream = connection.getInputStream();

        reader = new BufferedReader(new InputStreamReader(stream));

        StringBuffer buffer = new StringBuffer();

        String line = "";
        while ((line = reader.readLine()) != null){
            buffer.append(line);
        }

    } catch (MalformedURLException e){
        e.printStackTrace();
    } catch (IOException el){
        el.printStackTrace();
    } finally {
        connection.disconnect();
        reader.close()
    }*/
    private static final String LOGIN_REQUEST_URL = "localhost/accouts/login";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
