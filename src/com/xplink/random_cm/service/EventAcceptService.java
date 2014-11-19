package com.xplink.random_cm.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class EventAcceptService {
	private static final Logger logger = Logger.getLogger(EventAcceptService.class);
	private String fbAppId ;
	private String fbSecretKey ;
    private String fbCanvasPage ;

	public String eventDecode(String request_ids ) throws IOException{
		
		logger.debug("[IN: EventAcceptService]");
		
		logger.debug("[fbAppId: ]"+fbAppId);
		logger.debug("[fbSecretKey: ]"+fbSecretKey);
		logger.debug("[fbCanvasPage: ]"+fbCanvasPage);
   
	      String token_url = "https://graph.facebook.com/oauth/access_token?client_id="+ fbAppId +
          "&client_secret="+ fbSecretKey + "&grant_type=client_credentials";
		  
	  	  logger.debug("[token_url:]"+token_url);
	      
	  	 InputStream in = new URL( token_url ).openStream();
	  	 String apptoken;
	  	 
	      try {
	    	 apptoken = IOUtils.toString( in ); 
	    	  logger.debug( "[read data Graph api :]"+apptoken );
	    	  
	      } finally {
	        IOUtils.closeQuietly(in);
	      }
	      
	      String[] data_ids = request_ids.split(",\\s*");
	      
	      InputStream streamIn;
	      
	      JSONObject data = null;
	      
	      for (int i=0;i<data_ids.length;i++){	    	  
	    	  streamIn = new URL("https://graph.facebook.com/"+data_ids[i]+"?"+apptoken).openStream();
	    	  
	    	  data = (JSONObject)JSONSerializer.toJSON(IOUtils.toString(streamIn));	    	  
	    	  logger.debug("[JSON data:]"+data);    	      	  
	      }
	      
	      logger.debug("[data:]"+data.getString("data"));
	      
	      
		return data.getString("data");
			
	}
	
	public String getFbAppId() {
		return fbAppId;
	}

	public void setFbAppId(String fbAppId) {
		this.fbAppId = fbAppId;
	}

	public String getFbSecretKey() {
		return fbSecretKey;
	}

	public void setFbSecretKey(String fbSecretKey) {
		this.fbSecretKey = fbSecretKey;
	}
	
	public String getFbCanvasPage() {
		return fbCanvasPage;
	}

	public void setFbCanvasPage(String fbCanvasPage) {
		this.fbCanvasPage = fbCanvasPage;
	}

}
