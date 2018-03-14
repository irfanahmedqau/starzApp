package com.starz.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starz.util.UrlReaderUtil;


@Service
public class UrlReaderService {
	

	
public   Map<String, JSONObject> readFromURL(String readFromUrl,String filterParam , String levelParam){
	
	
	Map<String, JSONObject> jsonObjectStore = new HashMap<String, JSONObject>(); 
	
		
		try{
			ObjectMapper mapper =  new ObjectMapper();
		       JSONParser parser = new JSONParser();

			
		URL obj = new URL(readFromUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append("\n");
				response.append(inputLine);
			}
			in.close();
			jsonObjectStore =  parseJson(response.toString(),levelParam);

			// print result
			//System.out.println(response.toString());
			//mapper.readValue(response.toString());

	}else{
		System.out.println("**************SOME ERROR OCCURED *************");
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
		return jsonObjectStore;
		
	}
	
	public  Map<String, JSONObject> parseJson(String inputString,String level){
		
		JSONParser parser = new JSONParser();
		StringBuilder sb  =  new StringBuilder();
		Map<String, JSONObject> jsonObjectStore = new HashMap<String, JSONObject>(); 
	     try {
	        Object obj = parser.parse(inputString);
	        JSONObject jsonObject = (JSONObject) obj;

	        JSONArray cells = (JSONArray) jsonObject.get("entries");
	        	
	        Iterator<JSONObject> iterator = cells.iterator();
	        while(iterator.hasNext()){
	        	
	        	if(iterator.next().get("peg$contentClassification").equals("Uncensored")){
	        		jsonObjectStore.put("uncensored", iterator.next());
	        	}else if(iterator.next().get("peg$contentClassification").equals("Censored")){
	        		jsonObjectStore.put("censored", iterator.next());
	        	}

	           /*System.out.println(iterator.next().get("peg$contentClassification"));
	           sb.append(iterator.next().get("peg$contentClassification").toString());*/

	        }

	     } catch (Exception e) {

	      e.printStackTrace();

	     }
	     return jsonObjectStore;
	}
	
	

}
