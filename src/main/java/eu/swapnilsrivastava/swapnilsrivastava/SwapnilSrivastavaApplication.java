package eu.swapnilsrivastava.swapnilsrivastava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eu.swapnilsrivastava.swapnilsrivastava.services.SpeakerService;
import eu.swapnilsrivastava.swapnilsrivastava.services.SpeakerServiceImp;

@SpringBootApplication
public class SwapnilSrivastavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapnilSrivastavaApplication.class, args);

		SpeakerService service = new SpeakerServiceImp();

		System.out.println(service.findAll().get(0).getFirstName());
	}

}