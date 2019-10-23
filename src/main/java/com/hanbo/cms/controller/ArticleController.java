package com.hanbo.cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hanbo.cms.comon.ConstClass;
import com.hanbo.cms.entity.Article;
import com.hanbo.cms.entity.Cat;
import com.hanbo.cms.entity.Channel;
import com.hanbo.cms.entity.User;
import com.hanbo.cms.service.ArticleService;
import com.hanbo.cms.service.CatService;
import com.hanbo.cms.service.ChannelService;

@Controller
@RequestMapping("article")
public class ArticleController   {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ChannelService chanService;
	
	
	@Autowired
	CatService catService;
	
	
	//  显示一篇具体的文章
	@RequestMapping("show")
	public String show(HttpServletRequest request, Integer id) {
		
		Article  article = articleService.findById(id);
		System.out.println("article is " + article);
		request.setAttribute("article", article);
		return "article/detail";
	}
	
	 //跳转到添加的页面
	@RequestMapping(value = "add",method=RequestMethod.GET)
	public String show(HttpServletRequest request) {
		List<Channel> allChnls = chanService.getAllChnls();
		request.setAttribute("channels", allChnls);
		return "article/publish";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "add",method=RequestMethod.POST)
	public boolean add(HttpServletRequest request,Article article, MultipartFile file) throws IllegalStateException, IOException {
		
		processFile(file,article);
		
		//获取作者
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		return articleService.add(article)>0;
		
	}
	
	
	
	@RequestMapping(value = "update",method=RequestMethod.GET)
	public String update(HttpServletRequest request,Integer id) {
		
		List<Channel> allChnls = chanService.getAllChnls();
		Article article = articleService.findById(id);
		
		request.setAttribute("article", article);
		request.setAttribute("content1", article.getContent());
		request.setAttribute("channels", allChnls);
		return "my/update";
		
	}
	
	private void processFile(MultipartFile file,Article article) throws IllegalStateException, IOException {

		// 原来的文件名称
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "h:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		article.setPicture(destFileName.substring(7));
		
	}
	
	@RequestMapping(value = "update",method=RequestMethod.POST)
	@ResponseBody
	public boolean update(HttpServletRequest request,Article article, MultipartFile file) throws IllegalStateException, IOException {
		
		processFile(file,article);
		
		//获取作者
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		int result = articleService.update(article);
		
		return result > 0;
		
	}
	
	
	@RequestMapping(value="listCatByChnl",method=RequestMethod.GET)
	@ResponseBody
	public List<Cat> getCatByChnl(int chnlId){
		
		List<Cat> chnlList = catService.getListByChnlId(chnlId);
		return chnlList;
	}
	
	

}
