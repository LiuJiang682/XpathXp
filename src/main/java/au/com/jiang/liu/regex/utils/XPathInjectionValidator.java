package au.com.jiang.liu.regex.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class XPathInjectionValidator {

	private static final Pattern INJECTION_CHARACTER_PATTERN = Pattern.compile("[()=\\'\\[\\]:,*/ ]+");
	
	public static boolean isContainsXPathInjectionCharacter(String string) throws UnsupportedEncodingException {
		boolean isContainsInjectionChar = false;
		
		if (StringUtils.isNotBlank(string)) {
			// Always to avoid encoding evading....
			String decodedValue = URLDecoder.decode(string, Charset.defaultCharset().name());
			if (INJECTION_CHARACTER_PATTERN.matcher(decodedValue).find()) {
				isContainsInjectionChar = true;
			}
		}
		return isContainsInjectionChar;
	}

}
