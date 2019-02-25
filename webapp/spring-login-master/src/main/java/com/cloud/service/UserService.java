package com.cloud.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.cloud.model.User;
import com.cloud.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService{

	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AmazonSNSAsync amazonSNSClient;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    /*@PostConstruct
	public void initializeSNSClient() {

		this.amazonSNSClient = AmazonSNSAsyncClientBuilder.defaultClient();
	}*/

    public String getTime()
    {
    	return new Date().toString();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException(email + " was not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles())
        );
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        return userRepository.save(user);
    }
    
    
   /* public void sendMessage(String emailId) throws ExecutionException, InterruptedException {

      logger.info("Sending Message - {} ", emailId);
    
      String topicArn = getTopicArn("password_reset");
      PublishRequest publishRequest = new PublishRequest(topicArn, emailId);
      Future<PublishResult> publishResultFuture = amazonSNSClient.publishAsync(publishRequest);
      String messageId = publishResultFuture.get().getMessageId();

      logger.info("Send Message {} with message Id {} ", emailId, messageId);

    }

	public String getTopicArn(String topicName) {

		String topicArn = null;

		try {
			Topic topic = amazonSNSClient.listTopicsAsync().get().getTopics().stream()
					.filter(t -> t.getTopicArn().contains(topicName)).findAny().orElse(null);

			if (null != topic) {
				topicArn = topic.getTopicArn();
			} else {
				logger.info("No Topic found by the name : ", topicName);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		logger.info("Arn corresponding to topic name {} is {} ", topicName, topicArn);

		return topicArn;

	}*/

}