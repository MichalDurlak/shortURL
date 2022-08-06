package pl.michaldurlak.shortURL.repository;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class LinkFirebase {

    public static void getConnectionToFirebase() throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("firebase_credentials.json");
//        FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase_credentials.json");
//        FileInputStream serviceAccount = new FileInputStream(resourceAsStream);

//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(resourceAsStream))
                .build();

        FirebaseApp.initializeApp(options);

    }

    public static void createRecordInDatabaseFirebase(int dayEnd, String originalLink, String shortLink, int timeEnd) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        java.util.Map<String, Object> docData = new HashMap<>();
        docData.put("dayEnd", dayEnd);
        docData.put("originalLink", originalLink);
        docData.put("shortLink", shortLink);
        docData.put("timeEnd", timeEnd);
        ApiFuture<WriteResult> future = db.collection("links").document(shortLink).set(docData);
        System.out.println("Update time:" + future.get().getUpdateTime() + " Original Link:" + originalLink + " Short Link:" + shortLink + " Link date&time:" + timeEnd + " " + dayEnd);


    }

    public static void getRecordInDatabaseFirebase() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
//        Iterable<DocumentReference> listAllRecords = db.collection("links").listDocuments();
//        listAllRecords.forEach(record -> {
//            Iterable<CollectionReference> links = db.collection("links").document(record.getId()).listCollections();
//            links.forEach(x -> {
//
//                DocumentReference docRef = db.collection("cities").document(x.getId());
//
//            });
//
//
//        });

// asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("links").get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.get("dayEnd") +", " + document.get("originalLink") + ", " + document.get("timeEnd"));
        }

    }

    public static String getSpecificRecordInDatabaseFirebase(String shortURL) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                dbFirestore.collection("links").document(shortURL);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if(document.exists()) {
            return (String) document.get("originalLink");

        }else {
            // redirect to 404 not site not found
            return "BrakLinku";
        }

    }

}
