package jym.agtsys.util;

import java.io.FileOutputStream;

import org.junit.Before;
import org.junit.Test;

public class defaultImageCaptchaTest {
  private DefaultImageCaptcha dic;
	@Before
	public void setUp(){
	dic =new DefaultImageCaptcha();
	}
	
	@Test
	public void testDefaultImageCaptcha() throws Exception {
		FileOutputStream fos =new FileOutputStream("catpcha/test.png");
	    String cp=dic.generateCaptcha(fos);
	    System.out.println(cp);
	}

}
