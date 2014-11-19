package com.xplink.random_cm.dataencryption;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

//import com.mea.portlet.mail.service.Cryptographer;
//import com.mea.portlet.mail.service.HexString;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

public class Cryptographer {

	private static String algorithm = "DESede";
	private static Cipher cipher = null;
	
	

	/**
	 * Encrypt input string to byte array.
	 * This method encrypt data with normal cipher encryption.
	 * @param input String that want to encrypt.
	 * @param ikey the {@link SecretKey} to use for encryption.
	 * @return byte array after encrypt.
	 * @throws InvalidKeyException If invalid Keys (invalid encoding, wrong length, uninitialized, etc).
	 * @throws IllegalBlockSizeException If the length of data provided to a block cipher is incorrect, i.e., does not match the block size of the cipher. 
	 * @throws BadPaddingException If a particular padding mechanism is expected for the input data but the data is not padded properly. 
	 * @throws NoSuchAlgorithmException If a particular cryptographic algorithm is requested but is not available in the environment.
	 * @throws NoSuchPaddingException If a particular padding mechanism is requested but is not available in the environment. 
	 * @throws UnsupportedEncodingException 
	 * @throws Base64DecodingException 
	 */
	public static byte[] encrypt(String input, SecretKey ikey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, Base64DecodingException, UnsupportedEncodingException{
		if(ikey != null){
			algorithm = ikey.getAlgorithm();
		}
		setUp();
		cipher.init(Cipher.ENCRYPT_MODE, ikey);
		byte[] inputBytes = input.getBytes();
		return cipher.doFinal(inputBytes);
	}

	/**
	 * Encrypt input string to byteStringArray.
	 * This method encrypt data with normal cipher encryption and convert encrypted data from byteArray to byteStringArray.
	 * @param input String that want to encrypt.
	 * @param ikey the {@link SecretKey} to use for encryption.
	 * @return byteStringArray after encrypt, that is string represent value of each byte separate by comma, example "89,85,-12".
	 * @throws UnsupportedEncodingException 
	 * @throws Base64DecodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 * @throws InvalidKeyException If invalid Keys (invalid encoding, wrong length, uninitialized, etc).
	 * @throws IllegalBlockSizeException If the length of data provided to a block cipher is incorrect, i.e., does not match the block size of the cipher. 
	 * @throws BadPaddingException If a particular padding mechanism is expected for the input data but the data is not padded properly. 
	 * @throws NoSuchAlgorithmException If a particular cryptographic algorithm is requested but is not available in the environment.
	 * @throws NoSuchPaddingException If a particular padding mechanism is requested but is not available in the environment. 
	 * @throws UnsupportedEncodingException 
	 * @throws Base64DecodingException 
	 */
	public static String encryptToByteStringArray(String input, SecretKey ikey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, Base64DecodingException, UnsupportedEncodingException {
		byte[] encryptionBytes = null;
		String cipher = ""+encryptionBytes[0];
		for(int i = 1; i < encryptionBytes.length; i++){
			cipher = cipher+","+encryptionBytes[i];     	  
		}
		return cipher;
	}
	
	public static String encryptToHexString(String input, SecretKey ikey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, Base64DecodingException, UnsupportedEncodingException {
		byte[] encryptionBytes = null;
		encryptionBytes = Cryptographer.encrypt( input, ikey);
		return HexString.bufferToHex(encryptionBytes);
	}
	
	/**
	 * Encrypt input string to Hex String.
	 * This method encrypt data with normal cipher encryption and convert encrypted data from byteArray to Hex String.
	 * @param input String that want to encrypt.
	 * @param ikey the {@link SecretKey} to use for encryption.
	 * @return Hex String of encrypted input
	 * @throws InvalidKeyException 
	 * @throws InvalidKeyException If invalid Keys (invalid encoding, wrong length, uninitialized, etc).
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws IllegalBlockSizeException If the length of data provided to a block cipher is incorrect, i.e., does not match the block size of the cipher. 
	 * @throws BadPaddingException If a particular padding mechanism is expected for the input data but the data is not padded properly. 
	 * @throws NoSuchAlgorithmException If a particular cryptographic algorithm is requested but is not available in the environment.
	 * @throws NoSuchPaddingException If a particular padding mechanism is requested but is not available in the environment. 
	 * @throws BadPaddingException 
	 * @throws GeneralSecurityException 
	 */
	
	
	public static String decryptFromHexString(String source, SecretKey ikey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		String recovered = null; 
		if(source != null && !source.trim().equals("")){
			recovered = Cryptographer.decrypt(HexString.hexToBuffer(source), ikey);
		}
		return recovered;
	}


	public static String decryptFromByteStringArray(String source, SecretKey ikey) throws GeneralSecurityException{
		String recovered = null; 
		if(source != null && !source.trim().equals("")){
			String[] stArr = source.split(",");
			int len = stArr.length;
			byte[] de = new byte[len];
			for(int  j = 0; j < len; j++){
				de[j] = (new Byte(stArr[j])).byteValue();
			}
			recovered = Cryptographer.decrypt(de, ikey);
		}
		return recovered;
	}
	
	public static String decrypt(byte[] encryptionBytes, SecretKey ikey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		if(ikey != null){
			algorithm = ikey.getAlgorithm();
		}
		setUp();
		cipher.init(Cipher.DECRYPT_MODE, ikey);
		byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
		String recovered = new String(recoveredBytes);
		return recovered;	
	}
	
	/**
	 * Decrypt Hex String to string.
	 * This method convert Hex String to byteArray of encrypted data and decrypt data with normal cipher decryption .
	 * @param source Hex String of encrypted String.
	 * @param ikey the {@link SecretKey} to use for decryption.
	 * @return String after decrypt.
	 * @throws InvalidKeyException If invalid Keys (invalid encoding, wrong length, uninitialized, etc).
	 * @throws IllegalBlockSizeException If the length of data provided to a block cipher is incorrect, i.e., does not match the block size of the cipher. 
	 * @throws BadPaddingException If a particular padding mechanism is expected for the input data but the data is not padded properly. 
	 * @throws NoSuchAlgorithmException If a particular cryptographic algorithm is requested but is not available in the environment.
	 * @throws NoSuchPaddingException If a particular padding mechanism is requested but is not available in the environment. 
	 */
	

	
	public static SecretKey getKey(String algorithmName) throws NoSuchAlgorithmException{
		SecretKey key = KeyGenerator.getInstance(algorithmName).generateKey();	
		return key;
	}

	/**
	 * Write key to file in path from algorithmName.
	 * @param algorithmName String algorithm name to generateKey. If algorithmName is null or empty string method choose default algorithm(DESede).
	 * @param path to write file.
	 * @throws FileNotFoundException If a file with the specified pathname does not exist. 
	 * @throws IOException If an I/O exception of some sort has occurred.
	 * @throws NoSuchAlgorithmException If a particular cryptographic algorithm is requested but is not available in the environment.
	 */
	public static void writeKey(String algorithmName, String path) throws FileNotFoundException, IOException, NoSuchAlgorithmException{
		SecretKey key = getKey(algorithmName);
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos;
		oos = new ObjectOutputStream(fos);
		oos.writeObject(key);
		oos.close();	
	}

	private static void setUp() throws NoSuchAlgorithmException, NoSuchPaddingException{
		cipher = Cipher.getInstance(algorithm);	
	}
	
}
