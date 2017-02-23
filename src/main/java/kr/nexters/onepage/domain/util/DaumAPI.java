package kr.nexters.onepage.domain.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.locationImage.DayType;
import kr.nexters.onepage.domain.locationImage.LocationImageDto;

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
			JSONObject channel = (JSONObject)object.get("channel");
			JSONArray item = (JSONArray)channel.get("item");
			JSONObject array = (JSONObject)item.get(0);
			address = array.get("newAddress").toString();
			name = array.get("title").toString();
			String temp = name.substring(name.length()-2,name.length());
			if(temp.equals("구청") || temp.equals("시청"))
				name=name.substring(0,name.length()-1);
			System.out.println("name : " + name);
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
		return Location.of(Double.valueOf(lat), Double.valueOf(lng), name, NaverAPI.convertKorToEng(name), address);
	}

	private static String makeUrl(double latitude, double longitude) {
		StringBuilder urlString = new StringBuilder(
			"https://apis.daum.net/local/v1/search/keyword.json?");
		urlString.append("apikey=" + API_KEY);
		urlString.append("&location=");
		urlString.append(Double.toString(latitude));
		urlString.append(",");
		urlString.append(Double.toString(longitude));
		urlString.append("&radius=10000");
		urlString.append("&sort=0");
		urlString.append("&query=");
		String encodeResult = null;
		try {
			encodeResult = URLEncoder.encode("구청", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		urlString.append(encodeResult);

		return urlString.toString();
	}

	public static LocationImageDto getImageUrl(Location location, DayType dayType) {
		String url = makeUrl(location.getLatitude(), location.getLongitude());
		BufferedReader in = null;
		String imageUrl = null;
		String name = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

			InputStreamReader isr = new InputStreamReader(obj.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject) JSONValue.parse(isr);

			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			System.out.println("object : " + object);
			JSONObject channel = (JSONObject) object.get("channel");
			System.out.println(channel);
			JSONArray item = (JSONArray) channel.get("item");
			System.out.println(item);
			JSONObject array = (JSONObject) item.get(0);
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
		return LocationImageDto.of(location, imageUrl, dayType);
	}

	public static String Test(Double latitude, Double longitude){
		StringBuilder urlString  = new StringBuilder("https://apis.daum.net/local/v1/search/keyword.json?");
		urlString.append("apikey=" + API_KEY);
		urlString.append("&location=");
		urlString.append(Double.toString(latitude));
		urlString.append(",");
		urlString.append(Double.toString(longitude));
		urlString.append("&radius=3000");
		urlString.append("&sort=0");
		urlString.append("&query=");
		String encodeResult = null;
		try {
			encodeResult = URLEncoder.encode("마포구", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(encodeResult);
		urlString.append(encodeResult);

		BufferedReader in = null;
		String url = urlString.toString();
		String lng = null;
		String lat = null;
		String name = null;
		String address = null;
		JSONObject channel=null;
		System.out.println("url" + url);
		try {
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

			InputStreamReader isr = new InputStreamReader(obj.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject) JSONValue.parse(isr);

			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			channel = (JSONObject)object.get("channel");
			JSONArray item = (JSONArray)channel.get("item");
			JSONObject array = (JSONObject)item.get(0);
			address = array.get("newAddress").toString();
			name = array.get("title").toString();
			String temp = name.substring(name.length()-2,name.length());
			if(temp.equals("구청") || temp.equals("시청"))
				name=name.substring(0,name.length()-1);
			System.out.println("name : " + name);
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
		return channel.toString();
	}
}
