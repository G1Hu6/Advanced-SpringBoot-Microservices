package com.aop;

import com.aop.services.ShipmentService;
import com.aop.services.impl.ShipmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringbootAopApplicationTests {

	@Autowired
	ShipmentServiceImpl shipmentService;

	@Test
	void contextLoads() {
	}

	@Test
	void aopOrderPackage(){
		log.info("Test orderPackage");
		shipmentService.orderPackage(1L);
	}

	@Test
	void aopTrackPackage(){
		log.info("Test trackPackage");
		shipmentService.trackPackage(1L);
	}

}
