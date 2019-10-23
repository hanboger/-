package com.hanbo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.PageInfo;
import com.hanbo.cms.entity.Article;

public interface ArticleMapper {

	List<Article> list(@Param("chnId") Integer chnId, 
			@Param("catId") Integer catId);

	List<Article>  listHot();
	List<Article> listLast(int sum);

	Article findById(Integer articleId);

	int add(Article article);

	List<Article> listByUserId(Integer userId);

	@Update("UPDATE cms_article SET deleted=1 WHERE id=#{value} ")
	int deleteById(Integer id);
	
	
	@Update("UPDATE cms_article set title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},"
			+ "category_id=#{categoryId},updated=now() WHERE id=#{id}")
	int update(Article article);

	List<Article> listAdmin(@Param("status") Integer status);

	@Update("UPDATE cms_article set status=#{status},updated=now() WHERE id=#{articleId}")
	int updateStatus(@Param("articleId") Integer articleId, @Param("status") int status);

	@Update("UPDATE cms_article set hot=#{status},updated=now() "
			+ " WHERE id=#{articleId}")
	int updateHot(@Param("articleId") Integer articleId, @Param("status") int status);
	
}
