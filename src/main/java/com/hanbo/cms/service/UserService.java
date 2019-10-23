package com.hanbo.cms.service;

import com.hanbo.cms.entity.User;

public interface UserService {
	
	int register(User user) ;
	User login(User user);
	boolean checkUserExist(String username);
	

}
