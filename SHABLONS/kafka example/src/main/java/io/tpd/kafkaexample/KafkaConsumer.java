package io.tpd.kafkaexample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/*@Service
@RestController
public class KafkaConsumer {
	@KafkaListener(topics = "advice-topic", groupId = "groupId")
	public void consumer(String message) {
			System.out.println(message);
	}
	@KafkaListener(topics ="advice-topic",groupId ="groupId",containerFactory = "userkakfkalistener"  )
	public void f(PracticalAdvice advice) {
		System.out.println(advice.getMessage());
	}
}*/
