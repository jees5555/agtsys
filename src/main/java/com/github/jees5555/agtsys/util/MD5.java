package com.github.jees5555.agtsys.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static String bytesToHexString(byte[] src) {
		StringBuilder sb = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				sb.append(0);
			}
			sb.append(hv);
		}
		return sb.toString();
	}

	public static String md5encode(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MD5.bytesToHexString(md.digest(src.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
