package com.cygnet.Auction;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.config.SwaggerConfig;

@Import(SwaggerConfig.class)
@RestController
@SpringBootApplication
@EnableTransactionManagement
public class AuctionApplication {
	
//	static Logger logger = LoggerFactory.getLogger(AuctionApplication.class);

	@PostConstruct
	  public void init(){
	    // Setting Spring Boot SetTimeZone
	    TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	  }

	public static void main(String[] args) {
		SpringApplication.run(AuctionApplication.class, args);
		
	}
}

