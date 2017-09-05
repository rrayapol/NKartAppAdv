package com.nisum.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class NKartUtil {

	private static Key key;
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();
	private static Cipher c;

	static {

		try {
			key = generateKey();
			c = Cipher.getInstance(ALGORITHM);
		} catch (Exception e) {

		}
	}

	
	public static String generatePIN() {

		int randomPIN = (int) (Math.random() * 9000) + 1000;
		return String.valueOf(randomPIN);

	}

	public static String doEncrypt(String password) throws Exception {

		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = c.doFinal(password.getBytes());
		byte[] encryptedByteValue = new Base64().encode(encValue);
		return new String(encryptedByteValue, "UTF-8");

	}

	public static String doDecrypt(String password) throws Exception {

		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = new Base64().decode(password.getBytes());
		byte[] decryptedVal = c.doFinal(decodedValue);
		return new String(decryptedVal, "UTF-8");
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}

}
