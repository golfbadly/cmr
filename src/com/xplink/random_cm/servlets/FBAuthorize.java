/*package com.xplink.random_cm.servlets;

import java.net.URLEncoder;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.xplink.random_cm.service.FBAuthorizeService;

public class FBAuthorize extends AbstractController {
	private static final Logger logger = Logger.getLogger(FBAuthorizeService.class);
	//Facebook App info
    private String fbAppId ;
	private String fbSecretKey ;
    private String fbCanvasPage ;

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	logger.debug("IN: FBAuthorize");
    	
    	System.out.println("fbAppId="+fbAppId);
    	System.out.println("fbSecretKey="+fbSecretKey);
    	System.out.println("fbCanvasPage="+fbCanvasPage);
    	
    	HttpSession session = request.getSession();
        ModelAndView mav = new ModelAndView("agent");

        
        
        logger.debug("[sined request :]"+request.getParameter("signed_request"));
        
        String sign = request.getParameter("signed_request");
                
        //parse signed_request
        if( !sign.equals("null")) {
        	logger.debug("get signed_request");
            //it is important to enable url-safe mode for Base64 encoder 
            Base64 base64 = new Base64(true);
            
        
            //split request into signature and data
            String[] signedRequest = request.getParameter("signed_request").split("\\.", 2);          
            
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
            	logger.debug("[IN oauth]redirectUrl to oauth");
         
                //this is guest, create authorization url that will be passed to javascript
                //note that redirect_uri (page the user will be forwarded to after authorization) is set to fbCanvasUrl
                mav.addObject("redirectUrl", "https://www.facebook.com/dialog/oauth?client_id=" + fbAppId + 
                        "&redirect_uri=" + URLEncoder.encode(fbCanvasPage, "UTF-8") + 
                        "&scope=publish_stream,email");          
                             
                logger.debug("[OUT oauth]redirectUrl to oauth");
                          
            } else {
            	logger.debug("[IN accessToken]this is authorized user, get their info from Graph API using received access token");
                //this is authorized user, get their info from Graph API using received access token
                String accessToken = data.getString("oauth_token");
                FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
                logger.debug("[accessToken :]"+accessToken);
                session.setAttribute("accessToken", accessToken);
                User user = facebookClient.fetchObject("me", User.class);
                mav.addObject("user", user);
            }

        } else {
        	logger.debug(" //this page was opened not inside facebook // iframe possibly as a post-authorization redirect // do server side forward to facebook app");
            //this page was opened not inside facebook iframe,
            //possibly as a post-authorization redirect.
            //do server side forward to facebook app
            return new ModelAndView("LoginPage");
        }

    	logger.debug("OUT: FBAuthorize");
    	
        return mav;

    }

    //HmacSHA256 implementation 
    private String hmacSHA256(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] hmacData = mac.doFinal(data.getBytes("UTF-8"));
        return new String(hmacData);
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


}*/