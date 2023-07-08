package com.grishare.grishare;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GrishareApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	@Value("${RDS_USERNAME}")
	String userName;

	@Test
	public void username () throws Exception {
		System.out.println(userName);
	}

}
