package jym.agtsys.service;

import java.io.OutputStream;

public interface CaptchaService {
     String getCaptcha (OutputStream os) throws Exception;
}
