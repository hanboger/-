package com.hanbo.cms.service;

import java.util.List;

import com.hanbo.cms.entity.Cat;

public interface CatService {
	
	List<Cat> getListByChnlId(Integer id);  
	

}
