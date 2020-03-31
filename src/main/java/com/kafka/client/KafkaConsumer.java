package com.kafka.client;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	
//	@KafkaListener(topics = "Java_Topic", groupId = "testGroup")
//	public void listen(String message) {
//	    System.out.println("Received Messasge in group foo: " + message);
//	}
	
	@KafkaListener(topicPartitions=@TopicPartition(topic="Java_Topic",
			partitionOffsets= @PartitionOffset(initialOffset="0", partition="0")))
	public void listenAll(String message) {
	    System.out.println("Received All Messages in group foo: " + message);
	}

}
