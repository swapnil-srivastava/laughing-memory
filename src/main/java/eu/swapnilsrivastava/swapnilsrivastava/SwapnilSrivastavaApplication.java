package eu.swapnilsrivastava.swapnilsrivastava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SwapnilSrivastavaApplication {

  private final Producer producer;

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(SwapnilSrivastavaApplication.class);
    application.setWebApplicationType(WebApplicationType.NONE);
    application.run(args);
  }

  @Bean
  public CommandLineRunner CommandLineRunnerBean() {
    return (args) -> {
      this.producer.sendMessage("key", "value");
      MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("myConsumer");
      listenerContainer.start();
    };
  }

  @Autowired
  SwapnilSrivastavaApplication(Producer producer) {
    this.producer = producer;
  }

  @Autowired
  private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

}