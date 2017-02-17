package kr.nexters.onepage.domain.util;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.locationImage.LocationImageResponseDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DaumAPI {

	private final static String API_KEY = "85dba97fae53bf5787562fd449074f6b";

	public static Location makeLocation(Double latitude, Double longitude) {
		String url = makeUrl(latitude, longitude);
		BufferedReader in = null;
		String lng = null;
		String lat = null;
		String name = null;
		String address = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

			InputStreamReader isr = new InputStreamReader(obj.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject) JSONValue.parse(isr);

			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			System.out.println("object : " + object);
			JSONObject channel = (JSONObject)object.get("channel");
			System.out.println(channel);
			JSONArray item = (JSONArray)channel.get("item");
			System.out.println(item);
			JSONObject array = (JSONObject)item.get(0);
			System.out.println(array);
			address = array.get("newAddress").toString();
			name = array.get("title").toString();
			lat = array.get("latitude").toString();
			lng = array.get("longitude").toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return Location.of(Double.valueOf(lat), Double.valueOf(lng), name, name, address);
	}

	private static String makeUrl(double latitude, double longitude){
		StringBuilder urlString = new StringBuilder(
			"https://apis.daum.net/local/v1/search/keyword.json?");
		urlString.append("apikey=" + API_KEY);
		urlString.append("&location=");
		urlString.append(Double.toString(latitude));
		urlString.append(",");
		urlString.append(Double.toString(longitude));
		urlString.append("&radius=3000");
		urlString.append("&sort=0");
		urlString.append("&query=");
		String encodeResult=null;
		try {
			encodeResult = URLEncoder.encode("구청", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		urlString.append(encodeResult);

		return urlString.toString();
	}

	public static LocationImageResponseDto getImageUrl(Location location){
		String url = makeUrl(location.getLatitude(),location.getLongitude());
		BufferedReader in = null;
		String imageUrl = null;
		String name= null;
		try {
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

			InputStreamReader isr = new InputStreamReader(obj.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject) JSONValue.parse(isr);

			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			System.out.println("object : " + object);
			JSONObject channel = (JSONObject)object.get("channel");
			System.out.println(channel);
			JSONArray item = (JSONArray)channel.get("item");
			System.out.println(item);
			JSONObject array = (JSONObject)item.get(0);
			System.out.println(array);
			name = array.get("title").toString();
			imageUrl = array.get("imageUrl").toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return LocationImageResponseDto.of(location.getId(), imageUrl, name);
	}
}
