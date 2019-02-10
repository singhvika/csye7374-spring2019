package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class LoginApplication{

	//private static final Logger logger = LogManager.getLogger(LoginApplication.class);

	public static void main(String[] args) {
		//logger.info("Application started");

		SpringApplication.run(LoginApplication.class, args);
	}
	

	@RequestMapping(value={"/healthcheck"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String healthCheck(){
    	
    	return "Success";
    }

    

}
