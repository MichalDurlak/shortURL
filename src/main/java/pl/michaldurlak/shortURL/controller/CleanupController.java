package pl.michaldurlak.shortURL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michaldurlak.shortURL.repository.CleanupFirabseRecords;

import java.util.concurrent.ExecutionException;

@RestController
public class CleanupController {

    @GetMapping("/cleanup")
    public String cleanUpRecords() throws ExecutionException, InterruptedException {

        CleanupFirabseRecords.runCleaningService();
        return "Manually cleaned records";
    }
}
