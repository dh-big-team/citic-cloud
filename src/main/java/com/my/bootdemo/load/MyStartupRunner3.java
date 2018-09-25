package com.my.bootdemo.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=0)
public class MyStartupRunner3 implements CommandLineRunner{

	private static final Logger Log=LoggerFactory.getLogger(MyStartupRunner3.class);
	
	@Override
	public void run(String... args) throws Exception {
		Log.info("-------------------MyStartupRunner3 is running");
	}
	
}
