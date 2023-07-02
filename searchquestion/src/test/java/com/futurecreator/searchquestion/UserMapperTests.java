package com.futurecreator.searchquestion;


import com.futurecreator.dao.mapper.user.UserMapper;

import com.futurecreator.dao.pojo.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserMapperTests {
	@Resource
	private UserMapper userMapper;

	@Test
	void testUserMapperInsert(){
		User user = new User();
		user.setName("cxk");
		user.setPhone("18944796017");
		user.setPassword("cxkggg");
		System.out.println(userMapper.insert(user));
	}

	@Test
	void testUserMapperUpdate(){
		User user = new User();
		user.setName("cxk");
		//user.setPhone("18944796017");
		user.setPassword("cxkgg");
		System.out.println(userMapper.updateUserByPhone(user));
	}

}
