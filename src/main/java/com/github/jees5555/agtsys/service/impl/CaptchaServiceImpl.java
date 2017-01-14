package com.github.jees5555.agtsys.service.impl;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jees5555.agtsys.service.CaptchaService;
import com.github.jees5555.agtsys.util.Captcha;

@Service
public class CaptchaServiceImpl implements CaptchaService {

	@Autowired
	private Captcha c;

	@Override
	public String getCaptcha(OutputStream os) throws Exception {
		return c.generateCaptcha(os);
	}

}
