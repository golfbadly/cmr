package com.xplink.random_cm.service;


import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PaginationService {
	
	private static final Logger logger = LogManager.getLogger(PaginationService.class);
	private String pageSize;

	public int calculatePageAll(List productList){
		int size  = Integer.parseInt(pageSize);
		int pageNumber;
		if(productList.size()<size){
			pageNumber=1;
		}else{
			if(productList.size()%size==0){
			pageNumber = productList.size()/size;
			}else{
			pageNumber = (productList.size()/size)+1;
			}
		}
		
		return pageNumber;
	}
	
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
