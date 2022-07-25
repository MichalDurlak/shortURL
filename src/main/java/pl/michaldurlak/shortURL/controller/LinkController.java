package pl.michaldurlak.shortURL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.michaldurlak.shortURL.model.LinkModel;
import pl.michaldurlak.shortURL.service.LinkService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


@Controller
public class LinkController {


    //Return home page using spring thymeleaf
    @RequestMapping("/")
    public String getIndex(){
        //Show home page
        return "index";
    }

    //Actions after clicking submit button
    @PostMapping("/")
    public String getResultPage(@ModelAttribute("URLRequest") LinkModel linkModel, Model model) throws IOException, ExecutionException, InterruptedException {
        model.addAttribute("linkModel",linkModel);

        //Create short link
        LinkService.createShortLink(linkModel);

        //Show result page
        return "result";
    }

    //Take alias and return to the original site
    @ResponseBody
    @RequestMapping(value="/{shortLink}", method = RequestMethod.GET)
    public RedirectView redirectToOriginalSite(@PathVariable("shortLink") String shortLink) throws ExecutionException, InterruptedException {
        //Create view
        RedirectView redirectView = new RedirectView();
        //Set Url to the original
        redirectView.setUrl("https://" + LinkService.getOriginalSite(shortLink));
        //Redirect
        return redirectView;

    }




    // CAN SHARE API WITH THIS BELOW

    //Create alias - return String
//    @GetMapping("/createShort")
//    @ResponseBody
//    public String createShort(@RequestParam(required = true) String originalLink, int dateEnd, int timeEnd) throws IOException, ExecutionException, InterruptedException {
//        LinkModel linkModel = new LinkModel(originalLink, dateEnd, timeEnd);
//        LinkService.createShortLink(linkModel);
//        System.out.println(linkModel.toString());
//        return linkModel.toString();
//    }

    //Create alias - void
//    @GetMapping("/createShortVoid")
//    public void createShortVoid(@RequestParam(required = true) String originalLink, int dateEnd, int timeEnd) throws IOException, ExecutionException, InterruptedException {
//        LinkModel linkModel = new LinkModel(originalLink, dateEnd, timeEnd);
//        LinkService.createShortLink(linkModel);
//        System.out.println(linkModel.toString());
//    }



    // http://localhost:8080/createShort?originalLink=twitch.tv&dateEnd=17072022&timeEnd=111111






    //
//    @GetMapping("/test")
//    public void test() throws IOException, ExecutionException, InterruptedException {
//        LinkService.getRecords();
//    }


}
