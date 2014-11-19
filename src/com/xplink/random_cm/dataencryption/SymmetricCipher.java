package com.xplink.random_cm.dataencryption;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;

//import com.mea.portlet.mail.controller.SetUpUserNamePasswordController;
//import com.mea.portlet.mail.service.Cryptographer;
//import com.mea.portlet.mail.service.SymmetricCipher;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

public class SymmetricCipher {

	private static final Logger logger = Logger
	.getLogger(SymmetricCipher.class);

//	passWord = symmetricCipher.encryptString(passWord);
	private static SecretKey secretKey;
	
	private  String keyFile;
	
	
	public String encryptString(String text) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, Base64DecodingException, UnsupportedEncodingException {
		logger.debug("IN:encryptString");
		logger.debug("Text :" + text);
		String encryptString = Cryptographer.encryptToHexString(text, getSecretKey());
		logger.debug("OUT:encryptString");
		return encryptString;
	}
	
	public String decryptString(String text) throws GeneralSecurityException {
		logger.debug("IN:decryptString");
		String encryptString = Cryptographer.decryptFromHexString(text, getSecretKey());
		logger.debug("Text :" + encryptString);
		logger.debug("OUT:decryptString");
		return encryptString.toString();
	}
	
	public SecretKey getSecretKey() {
		if (secretKey != null) {
			return secretKey;
		}
		
		try {
			FileInputStream fis = new FileInputStream(keyFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			secretKey = (SecretKey) ois.readObject();
			ois.close();
		} catch (Exception e) {
			logger.fatal("Cannot load secretKey file", e);
		}
		return secretKey;
	}


	public String getKeyFile() {
		return keyFile;
	}
	
	public void setKeyFile(String keyFile) {
		this.keyFile = keyFile;
	}
	
	public static void setSecretKey(SecretKey secretKey) {
		SymmetricCipher.secretKey = secretKey;
	}
	
}
