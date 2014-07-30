package com.ece458.controller;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ece458.dao.TestDao;
import com.ece458.domain.Data;

@Controller
@RequestMapping("/")
public class StartController {

	private BigInteger key = null;
	SecretKeySpec secretKeySpec;
	final Provider bc = new BouncyCastleProvider();
	@Autowired
	TestDao testDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpSession session, Model model) {
		model.addAttribute("data", null);
		return new ModelAndView("welcome");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/lookup")
	public ModelAndView doLookup(HttpSession session, Model model,
			@RequestParam("domainName") String domainName)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			InvalidKeyException {
		boolean hasSign = false;
		Data data = testDao.getip(domainName);
		System.out.println(data);
		if (key != null && data != null) {

			byte[] calculateMac = calculateMac(data);
			data.setSignature(Arrays.toString(calculateMac));
			hasSign = true;
		}
		model.addAttribute("hasSign", hasSign);
		model.addAttribute("data", data);
		return new ModelAndView("lookup");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/lookup")
	public ModelAndView viewLookup(HttpSession session, Model model) {

		return new ModelAndView("lookup");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/dhkey")
	public ModelAndView doDiffiHellman(HttpSession session, Model model) {
		KeyExchange server = new KeyExchange();
		KeyExchange client = new KeyExchange(server);
		BigInteger ka = server.send();
		BigInteger kb = client.send();

		BigInteger serverKey = server.calculateKey(kb);
		BigInteger clientKey = client.calculateKey(ka);

		if (serverKey.equals(clientKey)) {
			key = serverKey;
			secretKeySpec = new SecretKeySpec(key.toString().getBytes(),
					"HMac-SHA1");

		}

		return new ModelAndView("lookup");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/rmkey")
	public ModelAndView clearKey(HttpSession session, Model model) {
		key = null;
		return new ModelAndView("lookup");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/verify")
	public ModelAndView verify(HttpSession session, Model model,
			@RequestParam("domainName") String domainName,
			@RequestParam("ip") String ip,
			@RequestParam("signature") String signature)
			throws InvalidKeyException, NoSuchAlgorithmException {

		if (key != null) {
			Data data = new Data(ip, domainName, signature);
			byte[] calculateMac = calculateMac(data);
			byte[] fromString = fromString(signature);
			System.out.println(Arrays.equals(calculateMac, fromString));
			model.addAttribute("sent", Arrays.toString(fromString));
			model.addAttribute("calc", Arrays.toString(calculateMac));
			model.addAttribute("equal", Arrays.equals(calculateMac, fromString));
			return new ModelAndView("verify");
		}
		return new ModelAndView("lookup");
	}

	public byte[] calculateMac(Data data) throws NoSuchAlgorithmException,
			InvalidKeyException {
		Mac mac = Mac.getInstance("HMac-SHA1", bc);
		mac.init(secretKeySpec);
		mac.reset();
		String message = data.getIp() + data.getDomainName();
		mac.update(message.getBytes(), 0, message.length());
		byte[] out = mac.doFinal();
		System.out.println("calculated mac:" + new String(out));
		return out;
	}

	public byte[] fromString(String sign) {
		sign = sign.substring(1, sign.length() - 1);
		System.out.println(sign);
		String[] tokens = sign.split(",");
		byte[] result = new byte[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			int parseInt = Integer.parseInt(tokens[i].trim());
			result[i] = (byte) parseInt;
		}
		return result;
	}

	private class KeyExchange {
		private int exponent;
		BigInteger g;
		BigInteger p;

		public KeyExchange() {
			SecureRandom secureRandom = new SecureRandom();
			g = BigInteger.probablePrime(512, secureRandom);
			p = BigInteger.probablePrime(512, secureRandom);
			exponent = (int) Math.round(Math.random() * 100);
		}

		public KeyExchange(KeyExchange seed) {
			g = seed.g;
			p = seed.p;
			exponent = (int) Math.round(Math.random() * 100);
		}

		public BigInteger send() {
			BigInteger ga = g.pow(exponent);
			BigInteger gaModp = ga.remainder(p);
			return gaModp;
		}

		public BigInteger calculateKey(BigInteger kb) {
			BigInteger calculatedKey = kb.pow(exponent).remainder(p);
			System.out.println("generated key: " + calculatedKey);
			return calculatedKey;
		}

	}

}
