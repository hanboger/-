package com.mmcro.cms.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.hanbo.cms.entity.Article;
import com.hanbo.cms.service.ArticleService;

public class TestArticle  extends BaseTest{
	
	@Autowired
	ArticleService arService;
	
	@Test
	public void testList() {
		int chnId= 3;
		PageInfo<Article> list = arService.list(3, 0, 1);
		if(list!=null && list.getList() != null) {
			list.getList().forEach(article->{
				System.out.println("article is " + article );
			});
		}
		
	} 

}
