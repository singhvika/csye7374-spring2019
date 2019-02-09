package com.cloud.util;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

public class Utils {

	/**
	 * Added to validate the date format
	 * 
	 * @return
	 */
	public static boolean validateDate(String date) {
		boolean isValid = false;

		if (date.matches("\\d{2}/\\d{2}/\\d{4}")) {
			isValid = true;
		}

		return isValid;
	}
	
	/**
	 * Added to generate a file name without spaces
	 * @param multiPart
	 * @return
	 */
	 public static String generateFileName(MultipartFile multiPart) {
	        return multiPart.getOriginalFilename().replace(" ", "_");
	    }
	 
	 /**
	  * Added to check the attachemnt extension
	 * @throws IOException 
	 * @throws MagicException 
	 * @throws MagicMatchNotFoundException 
	 * @throws MagicParseException 
	  */
	 public static boolean isValidExt(MultipartFile multipart) throws Exception
	 {
		 boolean isValid = true;
		 
		 byte[] data = multipart.getBytes();
		 MagicMatch match = Magic.getMagicMatch(data);
		 String mimeType = match.getMimeType();
		 
		 if(!(mimeType.contains("png") || mimeType.contains("jpeg") 
				 || mimeType.contains("jpg")))
		 {
			 isValid = false;
		 }
				 
		 return isValid; 
	 }
}
