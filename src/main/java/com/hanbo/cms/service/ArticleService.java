package com.hanbo.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hanbo.cms.entity.Article;


public interface ArticleService {
	
	PageInfo<Article> list(Integer chnId, Integer catId, Integer page);

	PageInfo<Article> hostList( Integer page);

	List<Article> last(int sum);

	Article findById(Integer articleId);

	int add(Article article);

	PageInfo<Article> listArticleByUserId(Integer id, Integer page);

	int remove(Integer id);

	int update(Article article);

	PageInfo<Article> getAdminArticles(Integer page,Integer status);
	
	int updateStatus(Integer articleId, int status);

	int updateHot(Integer articleId, int status);
	

}
