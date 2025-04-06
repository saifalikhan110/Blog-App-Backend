package com.saif.blog.blogApp;

import com.saif.blog.blogApp.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {


	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void repoTest(){
		String className = this.userRepo.getClass().getName();
		String package1 = this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(package1);

	}

}
