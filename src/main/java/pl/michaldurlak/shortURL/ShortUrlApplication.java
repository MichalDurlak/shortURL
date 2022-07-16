package pl.michaldurlak.shortURL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.michaldurlak.shortURL.repository.LinkFirebase;

import java.io.IOException;

@SpringBootApplication
public class ShortUrlApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ShortUrlApplication.class, args);
		LinkFirebase.getConnectionToFirebase();
	}




}
