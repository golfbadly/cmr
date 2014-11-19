package com.xplink.random_cm.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



public class PortletUtil {
	private static final Logger logger = Logger.getLogger(PortletUtil.class);


	public static String getSlideName(String slidePath) {
		File folder = new File(slidePath);
		String fnm="";
		if (folder != null) {
			File[] listOfFiles = folder.listFiles();
			if (listOfFiles != null && listOfFiles.length>0) {
				fnm=listOfFiles[0].getName();
			}
		} else {
			logger.debug("Folder " + slidePath + " is not exist");
		}
		logger.debug("fnm  :"+fnm);
		return fnm;
	}


}
