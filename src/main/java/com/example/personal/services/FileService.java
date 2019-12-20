package com.example.personal.services;

import com.example.personal.database.FirebaseAccess;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileService {
    private Bucket bucket;

    public FileService() {
        FirebaseAccess firebaseAccess = FirebaseAccess.getInstance();
        bucket = firebaseAccess.getBucket();
    }

    public byte[] getFile(String name) {
        return bucket.getStorage().get(BlobId.of(bucket.getName(), name)).getContent();
    }

    public void uploadFile(MultipartFile file) throws IOException {
        BlobId blobId = BlobId.of(bucket.getName(), file.getName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        bucket.getStorage().create(blobInfo, file.getBytes());
    }

    public void deleteFile(String file) {
        BlobId blobId = BlobId.of(bucket.getName(), file);
        bucket.getStorage().delete(blobId);
    }
}
