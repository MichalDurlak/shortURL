package pl.michaldurlak.shortURL.repository;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class LinkFirebase {

    public static void getConnectionToFirebase() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/shorturlbymd-firebase-adminsdk-lvfyv-e7f1b38ca5.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
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
        System.out.println(future.get().getUpdateTime());


    }
}
