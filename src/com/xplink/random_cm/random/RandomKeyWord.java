package com.xplink.random_cm.random;

import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xplink.random_cm.datamodel.KeywordBean;
import com.xplink.random_cm.managements.UpdateKeywordManager;

public class RandomKeyWord {

	private static final Logger logger = LogManager.getLogger(RandomKeyWord.class);
	private UpdateKeywordManager updateKeywordManager;
	
	public void randomKeyword(ArrayList<KeywordBean>arggen,int eventId){
		
		logger.debug("[IN randomKeywork :]"+arggen);
			
		ArrayList<KeywordBean> recipt = new ArrayList<KeywordBean>();
		ArrayList<KeywordBean> complete = new ArrayList<KeywordBean>();
				
		ArrayList<KeywordBean> gen = arggen;
		
		logger.debug("[Start random member] RandomKeyword.randomKeyword");		
		int r;
		int i=gen.size();
		int g=gen.size();	
		logger.debug("[size :]"+gen.size());
		
		while(g != 0){
			r=getRandom(g);
			
			if(r != i){	
				recipt.add(gen.get(r));	
				logger.debug("[data :]"+gen.get(r));	
				gen.remove(r);
				g--;
				i--;
			}
		}
        logger.debug("[Finish random member] RandomKeyword.randomKeyword");
		
        logger.debug("[Start set out keyword] RandomKeyword.randomKeyword");
        
		int n=1;
		for(int j=0;j<recipt.size()-1;j++){
			
				complete.add(recipt.get(n)); 
				n++;

		}
		
		complete.add(recipt.size()-1,recipt.get(0));
		
        logger.debug("[complete :]"+complete);
			
		for(int k=0;k<complete.size();k++){
			logger.debug("[complete :]"+complete.get(k).getEventDetailId());
			logger.debug("[inKeyword :]"+recipt.get(k).getInkeyword());
			updateKeywordManager.updateKeywordAfterRandom(complete.get(k).getEventDetailId(), recipt.get(k).getInkeyword());	
		}
        logger.debug("[Finish set out keyword] RandomKeyword.randomKeyword");	
        
	}
	public int getRandom(int n){
	
		Random randomNumberGenerator = new Random();
		int num = randomNumberGenerator.nextInt(n);
		return num;
	}
	
	public UpdateKeywordManager getUpdateKeywordManager() {
		return updateKeywordManager;
	}
	public void setUpdateKeywordManager(UpdateKeywordManager updateKeywordManager) {
		this.updateKeywordManager = updateKeywordManager;
	}
	
	
}
