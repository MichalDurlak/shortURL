package pl.michaldurlak.shortURL.service;
import org.springframework.stereotype.Service;
import pl.michaldurlak.shortURL.model.LinkModel;
import pl.michaldurlak.shortURL.repository.LinkFirebase;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class LinkService {



    public static void createShortLink(LinkModel linkModel) throws IOException, ExecutionException, InterruptedException {
        LinkFirebase.createRecordInDatabaseFirebase(linkModel.getDateEnd(), linkModel.getOriginalLink(), linkModel.getShortLink(), linkModel.getTimeEnd());
    }

    public static void getRecords() throws ExecutionException, InterruptedException {
        LinkFirebase.getRecordInDatabaseFirebase();
    }

    public static String getOriginalSite(String shortLink) throws ExecutionException, InterruptedException {
        return LinkFirebase.getSpecificRecordInDatabaseFirebase(shortLink);
    }
}
