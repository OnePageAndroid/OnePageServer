package kr.nexters.onepage.domain.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import kr.nexters.onepage.domain.location.Location;

public class GoogleLocation {

	private final static String API_KEY = "AIzaSyCrrl1KZfJQ-jUJuUMwLjP2oLRrGArLz7w";

	public static Location find(Double latitude, Double longitude){
		String url = makeUrl1(latitude,longitude,"");
		BufferedReader in = null;
		String lng=null;
		String lat=null;
		String name=null;
		String address=null;
		try{
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)obj.openConnection();

			InputStreamReader isr = new InputStreamReader(obj.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject)JSONValue.parse(isr);

			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			JSONArray bodyArray = (JSONArray) object.get("results");
			System.out.println("results" + bodyArray);
			JSONObject data = (JSONObject) bodyArray.get(1);
			JSONObject a1 = (JSONObject) data.get("geometry");
			JSONObject s2 = (JSONObject) a1.get("location");
			lng = s2.get("lng").toString();
			lat = s2.get("lat").toString();
			name = data.get("name").toString();
			address = data.get("vicinity").toString();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in !=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return Location.of(Double.valueOf(lat), Double.valueOf(lng), name, address);
	}

	private static String makeUrl1(double latitude, double longitude, String place) {
		StringBuilder urlString = new StringBuilder(
				"https://maps.googleapis.com/maps/api/place/search/json?");

		if (place.equals("")) {
			urlString.append("&location=");
			urlString.append(Double.toString(latitude));
			urlString.append(",");
			urlString.append(Double.toString(longitude));
			urlString.append("&rankBy=100");
			urlString.append("&sensor=false&key=" + API_KEY);
			urlString.append("&language=ko");
		} else {
			urlString.append("&location=");
			urlString.append(Double.toString(latitude));
			urlString.append(",");
			urlString.append(Double.toString(longitude));
			urlString.append("&rankBy=100");
			urlString.append("&types=" + place);
			urlString.append("&sensor=false&key=" + API_KEY);
			urlString.append("&language=ko");
		}
		return urlString.toString();
	}

}
