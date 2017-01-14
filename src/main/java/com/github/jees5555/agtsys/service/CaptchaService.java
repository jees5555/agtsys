package com.github.jees5555.agtsys.service;

import java.io.OutputStream;

public interface CaptchaService {
	String getCaptcha(OutputStream os) throws Exception;
}
