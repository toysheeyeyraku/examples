package org.springframework.integration.samples.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableIntegration
public class So52778707Application implements CommandLineRunner {

	@Autowired
	@Qualifier("errorRecovererFunction")
	@Lazy
	private Function<String, String> errorRecovererFlowGateway;

	/*@GetMapping("/{name}")
	public String getFromIntegrationGateway(@PathVariable("name") String param) throws Exception {

		return this.errorRecovererFlowGateway.apply(param).toString();
	}*/

	@Bean
	public IntegrationFlow errorRecovererFlow() {
		return IntegrationFlows.from(Function.class, "errorRecovererFunction")
				.split(new CustomSplitter(),"split")
				.aggregate().transform( List.class,t->{
					String ans ="";
					for (Object ch :t) {
						ans+=ch;
					}
					return ans;
				})
				.filter(Message.class, m -> {
					System.out.println(m.getPayload());
			if (m.getPayload().equals("f")) {
				return true;
			}
			return false;

		}).transform(t -> {
			Ar ar = new Ar();
			ar.X = 77;

			return ar;
		})//.handle(new Test(), "handle").get();
				.<Ar, Boolean>route(p -> p.X % 2 == 0, m -> m
				        .subFlowMapping(true, sf -> sf.<Ar>handle((p, h) ->{
				        	p.X*=10;
				        	System.out.println("YEEEEE");
				        	return p.X.toString();
				        	}))
				        
				        .subFlowMapping(false, sf -> sf.<Ar>handle((p, h) -> p.X.toString() ))).get();
	}
	public class  CustomSplitter {
		public List<Character> split(String s){
			List<Character> ans =new ArrayList<Character>();
			for (Character ch : s.toCharArray()) {
				ans.add(ch);
			}
			return ans;
		}
	}


	

	public static void main(String[] args) {
		SpringApplication.run(So52778707Application.class, args);
	}

	class Test {
		public String handle(Ar args, Map<String, Object> headers) throws IOException, TimeoutException {
			/*ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			try (Connection connection = factory.newConnection();
			     Channel channel = connection.createChannel()) {
				channel.queueDeclare("queueChannel", false, false, false, null);
				String message = "Hello World!";
				channel.basicPublish("", "queueChannel", null, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
			}
			return args.X.toString();*/
			return "d";
		}
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
	 
}