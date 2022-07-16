package pl.michaldurlak.shortURL.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.michaldurlak.shortURL.model.LinkModel;
import pl.michaldurlak.shortURL.service.LinkService;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class LinkController {


    @GetMapping("/test")
    public void test() throws IOException, ExecutionException, InterruptedException {

    }

    @GetMapping("/createShort")
    public String createShort(@RequestParam(required = true) String originalLink, int dateEnd, int timeEnd) throws IOException, ExecutionException, InterruptedException {

        LinkModel linkModel = new LinkModel(originalLink, dateEnd, timeEnd);
        LinkService.createShortLink(linkModel);

        return linkModel.toString();
    }


    // http://localhost:8080/createShort?originalLink=https://o2.pl&dateEnd=17072022&timeEnd=111111
}
