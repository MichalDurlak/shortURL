package pl.michaldurlak.shortURL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.michaldurlak.shortURL.repository.CleanupFirabseRecords;
import pl.michaldurlak.shortURL.repository.LinkFirebase;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableScheduling
public class ShortUrlApplication {

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		SpringApplication.run(ShortUrlApplication.class, args);
		LinkFirebase.getConnectionToFirebase();

		//Run cleanup records in firebase service
		CleanupFirabseRecords.runCleaningService();
	}

}
