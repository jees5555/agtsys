package com.github.jees5555.agtsys.util;

import java.io.OutputStream;

import com.github.bingoohuang.patchca.service.CaptchaService;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;

public class DefaultImageCaptcha implements Captcha {
	private CaptchaService cs;

	public DefaultImageCaptcha() {
		cs = new DefaultCaptchaService();
	}

	@Override
	public String generateCaptcha(OutputStream os) throws Exception {
		String captchca = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
		os.close();
		return captchca;
	}

	public void setCs(DefaultCaptchaService cs) {
		this.cs = cs;
	}

}
