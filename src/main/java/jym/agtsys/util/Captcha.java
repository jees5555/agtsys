package jym.agtsys.util;

import java.io.OutputStream;

public interface Captcha {

	public String generateCaptcha(OutputStream os)throws Exception;
}
