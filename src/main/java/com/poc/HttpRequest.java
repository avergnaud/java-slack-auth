package com.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class HttpRequest {

	public JsonObject queryPublic(String urlString) {

		URL url = null;
		HttpsURLConnection con = null;
		try {
			url = new URL(urlString);
			con = (HttpsURLConnection) url.openConnection();
			
			con.setConnectTimeout(30_000);//10 secondess
			
//			con.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
//			con.setRequestMethod("POST");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuilder response = new StringBuilder();
		try (InputStream inputStream = con.getInputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));) {
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		String result = response.toString();
		
		JsonObject jsonObject = null;
		try(JsonReader reader = Json.createReader(new StringReader(result));) {
			jsonObject = reader.readObject(); 
			
		}
		/* TODO check que c'est null lorsque timeout Kraken */
		return jsonObject;
	}
				
	
}
