package com.ece458.controller;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SecurityHelper {
	protected static final String ALGORITHM = "RSA";
	private KeyPair keyPair;

	public SecurityHelper() {
		Security.addProvider(new BouncyCastleProvider());
		setKeyPair(generateKey());
	}

	public SecurityHelper(KeyPair keyPair) {
		Security.addProvider(new BouncyCastleProvider());

	}

	private KeyPair generateKey() {
		KeyPairGenerator keyGen;
		KeyPair key = null;
		try {
			keyGen = KeyPairGenerator.getInstance(ALGORITHM);
			keyGen.initialize(256);
			key = keyGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return key;
	}

	public String sign(String message) throws NoSuchAlgorithmException,
			InvalidKeyException, SignatureException {
		Signature signature = Signature.getInstance(ALGORITHM);
		signature.initSign(keyPair.getPrivate(), new SecureRandom());
		signature.update(message.getBytes());
		byte[] sign = signature.sign();
		String signatureString = new String(sign);
		return signatureString;
	}

	public boolean verify(String message, String signatureString)
			throws SignatureException, InvalidKeyException,
			NoSuchAlgorithmException {
		Signature signature = Signature.getInstance(ALGORITHM);
		signature.initVerify(keyPair.getPublic());
		signature.update(message.getBytes());
		return signature.verify(signatureString.getBytes());
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

}
