package pl.michaldurlak.shortURL.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.michaldurlak.shortURL.model.LinkModel;
import pl.michaldurlak.shortURL.service.LinkService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class LinkController {


    @GetMapping("/test")
    public void test() throws IOException, ExecutionException, InterruptedException {
        LinkService.getRecords();
    }

    @ResponseBody
    @RequestMapping(value="/{shortLink}", method = RequestMethod.GET)
    public RedirectView redirectToOriginalSite(@PathVariable("shortLink") String shortLink) throws ExecutionException, InterruptedException {
        RedirectView redirectView = new RedirectView();
        System.out.println(LinkService.getOriginalSite(shortLink));
        redirectView.setUrl("https://" + LinkService.getOriginalSite(shortLink));
        return redirectView;

    }

    @GetMapping("/createShort")
    public String createShort(@RequestParam(required = true) String originalLink, int dateEnd, int timeEnd) throws IOException, ExecutionException, InterruptedException {

        LinkModel linkModel = new LinkModel(originalLink, dateEnd, timeEnd);
        LinkService.createShortLink(linkModel);

        return linkModel.toString();
    }


    // http://localhost:8080/createShort?originalLink=twitch.tv&dateEnd=17072022&timeEnd=111111
}
