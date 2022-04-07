package com.sieun.metaChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //데이터 베이스 붙일 때 어노테이션 옆에 괄호까지 없애주면 됨
public class MetaChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaChatApplication.class, args);
	}

}
