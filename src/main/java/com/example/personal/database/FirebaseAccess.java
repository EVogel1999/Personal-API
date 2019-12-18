package com.example.personal.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.FileInputStream;

public class FirebaseAccess {

    private static FirebaseAccess access;

    private Bucket bucket;

    private FirebaseAccess() {
        try {
            Dotenv dotenv = Dotenv.load();

            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/firebase-admin-creds.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(dotenv.get("FIREBASE_CONNECTION"))
                    .setStorageBucket(dotenv.get("FIRESTORE_BUCKET"))
                    .build();

            FirebaseApp.initializeApp(options);

            bucket = StorageClient.getInstance().bucket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FirebaseAccess getInstance() {
        if (access == null)
            access = new FirebaseAccess();
        return access;
    }

    public Bucket getBucket() {
        return bucket;
    }
}
