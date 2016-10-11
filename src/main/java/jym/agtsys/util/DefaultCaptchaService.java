package jym.agtsys.util;

import java.awt.Color;

import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.AbstractCaptchaService;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

public class DefaultCaptchaService extends AbstractCaptchaService{

	 //配置自己的验证码样式
	public DefaultCaptchaService() {
	RandomFontFactory rff=	new RandomFontFactory();
	rff.setMaxSize(27);
	rff.setMinSize(27);
	fontFactory = rff;
	RandomWordFactory rwf=new RandomWordFactory();
	rwf.setCharacters("ABCDEFGHJKMNOPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz1234567890");
	rwf.setMaxLength(4);
	rwf.setMinLength(4);
	wordFactory = rwf;
	colorFactory=new SingleColorFactory(Color.RED);
	backgroundFactory=new SingleColorBackgroundFactory();
	textRenderer =new BestFitTextRenderer();
	filterFactory = new CurvesRippleFilterFactory(colorFactory);;
	width=100;
	height=30;
	
	}

}
