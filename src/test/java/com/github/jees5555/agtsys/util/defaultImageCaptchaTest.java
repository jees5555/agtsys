package com.github.jees5555.agtsys.util;

import java.io.FileOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.github.jees5555.agtsys.util.DefaultImageCaptcha;

public class defaultImageCaptchaTest {
	private DefaultImageCaptcha dic;

	@Before
	public void setUp() {
		dic = new DefaultImageCaptcha();
	}

	@Test
	public void testDefaultImageCaptcha() throws Exception {
		FileOutputStream fos = new FileOutputStream("captcha/captcha.png");
		String cp = dic.generateCaptcha(fos);
		System.out.println(cp);
	}

}
