package com.starz.controller;

import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starz.service.UrlReaderService;
import com.starz.util.UrlReaderUtil;



@RestController
public class UrlReaderController {
	
	@Autowired
	UrlReaderService readerService;
	/*@Autowired
	UrlReaderUtil readerUtil;*/
	
	
	
	@RequestMapping( value="/media" , method=RequestMethod.GET)
	public Object getMovieStatus(@RequestParam String filter ,@RequestParam String level){
		
		UrlReaderUtil readerUtil = new UrlReaderUtil();
		 
		//"https://de8a7d97-b45e-401b-b30e-39ae7b922405.mock.pstmn.io/api/v1.0/mediaCatalog/titles/movies"

		Map<String, JSONObject> outputMap = readerService.readFromURL(readerUtil.readUrlFromPropertyFile(),filter, level);
		
		 Iterator it = outputMap.entrySet().iterator();
		 Map.Entry pair = null; 
		    while (it.hasNext()) {
		    	pair =  (Map.Entry)it.next();
		        /*System.out.println(pair.getKey() + " = " + pair.getValue());
		        //it.remove(); // avoids a ConcurrentModificationException
*/		    }
			return pair.getValue();
		
	}

}
