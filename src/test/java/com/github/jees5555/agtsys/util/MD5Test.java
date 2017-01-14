package com.github.jees5555.agtsys.util;

import org.junit.Test;

import com.github.jees5555.agtsys.util.MD5;

public class MD5Test {

	@Test
	public void testMd5() {
		System.out.println(MD5.md5encode("123456"));
	}

}
