package com.cloud;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoginApplication{

	private static final Logger logger = LoggerFactory.getLogger(LoginApplication.class);

	public static void main(String[] args) {
		
		logger.info("Application started");
		SpringApplication.run(LoginApplication.class, args);
	}
	

	@RequestMapping(value={"/ping"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String ping(HttpServletRequest request){

		logger.info("ping() " + request.getRemoteAddr());
    	return "pong";
    }
	
	@RequestMapping(value={"/healthcheck"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String time(){
    	
		logger.info("healthcheck Success");
    	return "Success";
    }

    

}
