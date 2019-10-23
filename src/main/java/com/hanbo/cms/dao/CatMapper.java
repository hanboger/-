package com.hanbo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hanbo.cms.entity.Cat;
import com.hanbo.cms.entity.Channel;

@Mapper
public interface CatMapper {

	@Select("SELECT id,name,channel_id channelId "
			+ " FROM cms_category "
			+ " WHERE channel_id=#{value}")
	List<Cat> selectByChnlId(Integer chnlId);
	
	
	/*@Select("SELECT * FROM cms_category WHERE id = #{value} limit 1")*/
	Cat findById(Integer id);

}
