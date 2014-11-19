package com.xplink.random_cm.util;

public class StringUtil {

	public static String removeHtmlTag(String text) {
		String shortText = text.replaceAll("\\<[^>]*>", "");
		shortText = shortText.replace("&quot", "");
		shortText = shortText.replace("&asm", "");
		shortText = shortText.replace("&nbsp", "");
		shortText = shortText.replace("&ldquo;", "");
		shortText = shortText.replace("&rdquo;", "");
		return shortText;
	}

	public static String Unicode2ASCII(String unicode) {

		StringBuffer ascii = new StringBuffer(unicode);

		int code;

		for (int i = 0; i < unicode.length(); i++) {

			code = (int) unicode.charAt(i);

			if ((0xE01 <= code) && (code <= 0xE5B))

				ascii.setCharAt(i, (char) (code - 0xD60));

		}

		return ascii.toString();

	}

	public static String ASCII2Unicode(String ascii) {

		StringBuffer unicode = new StringBuffer(ascii);

		int code;

		for (int i = 0; i < ascii.length(); i++) {

			code = (int) ascii.charAt(i);

			if ((0xA1 <= code) && (code <= 0xFB))

				unicode.setCharAt(i, (char) (code + 0xD60));

		}

		return unicode.toString();

	}

	public static String getByteUtfString(String text) throws Exception {
		return new String(text.getBytes("ISO8859-1"), "UTF-8");
	}

	public static String getFileType(String filename) throws Exception {

		int beginIndex = filename.indexOf(".");
		int endIndex = filename.length();
		String type = filename.substring(beginIndex + 1, endIndex);

		return type;
	}
	
    
   

}
