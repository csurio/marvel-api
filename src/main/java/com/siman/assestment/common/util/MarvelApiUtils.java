package com.siman.assestment.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MarvelApiUtils {
	
	public static String generateMD5(String input) throws NoSuchAlgorithmException {
		MessageDigest md     = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(input.getBytes());
		
		//Convert Byte Array to HEX
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			String hex = Integer.toHexString(0xFF & b);
			if(hex.length()==1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
