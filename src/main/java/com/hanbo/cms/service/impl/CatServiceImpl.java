package com.hanbo.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbo.cms.dao.CatMapper;
import com.hanbo.cms.entity.Cat;
import com.hanbo.cms.service.CatService;

@Service
public class CatServiceImpl implements CatService{
	
	@Autowired
	CatMapper catMapper;

	@Override
	public List<Cat> getListByChnlId(Integer id) {
		// TODO Auto-generated method stub
		return catMapper.selectByChnlId(id);
	}
	

}
