package com.hanbo.cms.dao;


import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hanbo.cms.entity.Channel;

public interface ChannelMapper {

	@Select("SELECT * FROM cms_channel ORDER BY id")
	List<Channel> listAll();
	
	/*@Select("SELECT * FROM cms_channel WHERE id = #{value} limit 1")*/
	Channel findById(Integer id);
	
	

}
