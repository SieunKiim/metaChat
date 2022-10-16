package com.sieun.metaChat;

import com.sieun.metaChat.entity.Building;
import com.sieun.metaChat.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication //데이터 베이스 붙일 때 어노테이션 옆에 괄호까지 없애주면 됨
public class MetaChatApplication  implements CommandLineRunner {
	private final BuildingRepository buildingRepository;

	public static void main(String[] args) {
		SpringApplication.run(MetaChatApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("[INIT] 대학 건물 데이터 저장");
		buildingRepository.save(new Building("baecknyeon", 37.337301, 127.265805));
		buildingRepository.save(new Building("gonghack", 37.337566, 127.267790));
		buildingRepository.save(new Building("huseang", 37.337753, 127.268633));
		buildingRepository.save(new Building("gisook", 37.334856, 127.263103));
		buildingRepository.save(new Building("doseo", 37.336612, 127.268472));
		buildingRepository.save(new Building("gyoyang", 37.339796, 127.272037));
		buildingRepository.save(new Building("inkyeong", 37.339729, 127.274556));
		buildingRepository.save(new Building("auhmun", 37.338213, 127.273026));
	}
}