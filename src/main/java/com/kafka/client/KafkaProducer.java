package com.kafka.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping("/kafka/publish/{message}")
	public void publishMessage(@PathVariable("message")String message) {
		ListenableFuture<SendResult<String, String>> future = 
				kafkaTemplate.send("Java_Topic",message);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			 
	        @Override
	        public void onSuccess(SendResult<String, String> result) {
	            System.out.println("Sent message=[" + message + 
	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        @Override
	        public void onFailure(Throwable ex) {
	            System.out.println("Unable to send message=["
	              + message + "] due to : " + ex.getMessage());
	        }
	    });
		
	}
}
