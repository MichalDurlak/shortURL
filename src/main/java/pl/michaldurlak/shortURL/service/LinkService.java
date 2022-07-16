package pl.michaldurlak.shortURL.service;
import pl.michaldurlak.shortURL.model.LinkModel;
import pl.michaldurlak.shortURL.repository.LinkFirebase;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class LinkService {


    public static void createShortLink(LinkModel linkModel) throws IOException, ExecutionException, InterruptedException {
        LinkFirebase.createRecordInDatabaseFirebase(linkModel.getDateEnd(), linkModel.getOriginalLink(), linkModel.getShortLink(), linkModel.getTimeEnd());

    }
}
