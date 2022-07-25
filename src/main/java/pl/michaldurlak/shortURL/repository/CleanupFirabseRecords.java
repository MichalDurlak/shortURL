package pl.michaldurlak.shortURL.repository;

import com.google.api.client.util.DateTime;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class CleanupFirabseRecords {


    public static String cleanUpService() throws ExecutionException, InterruptedException {

        // called function to get all expired links
        ArrayList<String> filterListOfRecords = getFilterListOfRecords();

        //delete all expired short links
        return deleteExpiredRecords(filterListOfRecords);
    }

    private static ArrayList<String> getFilterListOfRecords() throws ExecutionException, InterruptedException {
        //set date and time to check if record expired or not
        LocalDateTime dateAndTime = LocalDateTime.now();

        String date = dateAndTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String time = dateAndTime.format(DateTimeFormatter.ofPattern("HHmm"))+"00";

        //set up connection to database
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("links").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        //Create list with shortAddresses that need to be deleted
        ArrayList<String> shortAddresses = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            if(date.compareTo(String.valueOf(document.get("dayEnd")))>0){
//                System.out.println("("+date.compareTo(String.valueOf(document.get("dayEnd")))+") "+document.getId()+" / " +document.get("dayEnd") + " -> " + document.get("originalLink"));
                shortAddresses.add(document.getId());
            } else if (date.compareTo(String.valueOf(document.get("dayEnd"))) == 0 && time.compareTo(String.valueOf(document.get("timeEnd"))) > 0){
                shortAddresses.add(document.getId());
            }
        }

        return shortAddresses;
    }


    private static String deleteExpiredRecords(ArrayList<String> listOfExpiredShortLinks){

        //set up connection to database
        Firestore db = FirestoreClient.getFirestore();

        for(String shortLink : listOfExpiredShortLinks){
            db.collection("links").document(shortLink)
                    .delete();
        }

        return "CLEANED";
    }
}
