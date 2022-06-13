package Twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TwitterApplication
{
	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
	}

	@GetMapping("/helloSM")
	public String hello(@RequestParam (value = "socialMedia", defaultValue = "Twitter") String socialMedia)
	{
		return String.format("Hello %s!!!", socialMedia);
	}
}
