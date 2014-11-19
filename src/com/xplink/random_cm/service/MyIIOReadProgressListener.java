package com.xplink.random_cm.service;

import javax.imageio.ImageReader;
import javax.imageio.event.IIOReadProgressListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyIIOReadProgressListener implements IIOReadProgressListener{
	private static final Logger logger = LogManager
	.getLogger(MyIIOReadProgressListener.class);
	  public void imageComplete(ImageReader source) {
		  logger.debug("image complete " + source);
	  }

	  public void imageProgress(ImageReader source, float percentageDone) {
		  logger.debug("image progress " + source + ": " + percentageDone + "%");
	  }

	  public void imageStarted(ImageReader source, int imageIndex) {
		  logger.debug("image #" + imageIndex + " started " + source);
	  }

	  public void readAborted(ImageReader source) {
		  logger.debug("read aborted " + source);
	  }

	  public void sequenceComplete(ImageReader source) {
		  logger.debug("sequence complete " + source);
	  }

	  public void sequenceStarted(ImageReader source, int minIndex) {
		  logger.debug("sequence started " + source + ": " + minIndex);
	  }

	  public void thumbnailComplete(ImageReader source) {
		  logger.debug("thumbnail complete " + source);
	  }

	  public void thumbnailProgress(ImageReader source, float percentageDone) {
		  logger.debug("thumbnail started " + source + ": " + percentageDone + "%");
	  }

	  public void thumbnailStarted(ImageReader source, int imageIndex, int thumbnailIndex) {
		  logger.debug("thumbnail progress " + source + ", " + thumbnailIndex + " of "
	        + imageIndex);
	  }
	}
