package com.xplink.random_cm.service;

import java.net.URLEncoder;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class FBAuthorizeService  {
	private static final Logger logger = Logger.getLogger(FBAuthorizeService.class);
	
	private String redirectUrl;
	private String accessToken;
	
	//Facebook App info
    private String fbAppId ;
	private String fbSecretKey ;
    private String fbCanvasPage ;

   public String authorize(String sign) throws Exception {
    	
    	logger.debug("IN: FBAuthorize");
    	
    	/*System.out.println("fbAppId :"+fbAppId);
    	System.out.println("fbSecretKey :"+fbSecretKey);
    	System.out.println("fbCanvasPage :"+fbCanvasPage);*/
    	
    	logger.debug("fbAppId :"+fbAppId);
    	logger.debug("fbSecretKey :"+fbSecretKey);
    	logger.debug("fbCanvasPage :"+fbCanvasPage);
    	
        //Facebook App info
        

        logger.debug("[sined request :]"+sign);
                
        //parse signed_request

        	logger.debug("get signed_request");
            //it is important to enable url-safe mode for Base64 encoder 
            Base64 base64 = new Base64(true);
            
        
            //split request into signature and data
            String[] signedRequest = sign.split("\\.", 2);          
            
            //parse signature
            logger.debug("[sig 0 before decode:]"+signedRequest[0]);
            
            String sig = new String(base64.decode(signedRequest[0].getBytes("UTF-8")));
            logger.debug("[sig 0:]"+sig);
            logger.debug("[sig 1:]"+signedRequest[1]);
            
            
            //parse data and convert to json object
            String payload = new String(base64.decode(signedRequest[1].getBytes("UTF-8")));
            logger.debug("[payload  :]"+payload);
              
            
            JSONObject data = (JSONObject)JSONSerializer.toJSON(payload);
            logger.debug("[JSON data:]"+data);
            
            //check signature algorithm
            if(!data.getString("algorithm").equals("HMAC-SHA256")) {
            	logger.debug("unknown algorithm is used");
            	//unknown algorithm is used
                return null;
            }

            //check if data is signed correctly
            if(!hmacSHA256(signedRequest[1], fbSecretKey).equals(sig)) {
            	logger.debug("signature is not correct, possibly the data was tampered with");
                //signature is not correct, possibly the data was tampered with
                return null;
            }

            //check if user authorized the app
            if(!data.has("user_id") || !data.has("oauth_token")) {
            	System.out.println("####################registerApp");
            	logger.debug("[IN oauth]redirectUrl to oauth");
               
                this.redirectUrl = "https://www.facebook.com/dialog/oauth?client_id=" + fbAppId + 
                "&redirect_uri=" + URLEncoder.encode(fbCanvasPage, "UTF-8") + 
                "&scope=publish_stream,email";
                
                logger.debug("[OUT oauth]redirectUrl to oauth");
                return "redirect";
                          
            } else {
            	logger.debug("[IN accessToken]this is authorized user, get their info from Graph API using received access token");
                //this is authorized user, get their info from Graph API using received access token
                accessToken = data.getString("oauth_token");
                
                return "accessToken";

            }    	
    }

    //HmacSHA256 implementation 
    private String hmacSHA256(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] hmacData = mac.doFinal(data.getBytes("UTF-8"));
        return new String(hmacData);
    }

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public String getAccessToken() {
		return accessToken;
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