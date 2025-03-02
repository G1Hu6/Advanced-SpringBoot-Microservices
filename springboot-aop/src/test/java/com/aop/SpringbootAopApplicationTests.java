package com.aop;

import com.aop.services.ShipmentService;
import com.aop.services.impl.PaymentServiceImpl;
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

	@Autowired
	PaymentServiceImpl paymentService;

	@Test
	void contextLoads() {
	}

	@Test
	void aopOrderPackage(){
		log.info("Test orderPackage");
		System.out.println(shipmentService.orderPackage(1L));
	}

	@Test
	void aopTrackPackage(){
		log.info("Test trackPackage");
		shipmentService.trackPackage(1L);
	}

	@Test
	void aopOrderPackageInsidePaymentService(){
		log.info("Test OrderPackageInsidePaymentService");
		paymentService.orderPackage(4L);
	}

	@Test
	void aopProcessPayment(){
		log.info("Test processPayment");
		paymentService.processPayment(2L);
	}



}
