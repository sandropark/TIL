package org.sandro.s3uploadtest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/s3")
@RestController
public class S3Controller {
    private static final List<PartETag> PARTS = new ArrayList<>();

    private final AmazonS3 s3Client;

    public S3Controller(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @PostMapping("/upload-init")
    public InitiateMultipartUploadResult uploadInit(@RequestBody UploadInitReq req) {
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(req.bucketName, req.key);
        return s3Client.initiateMultipartUpload(initiateMultipartUploadRequest);
    }

    @PutMapping("/upload-part")
    public void uploadPart(@RequestBody MultiPartUploadRequest req) throws IOException {
        File file = new ClassPathResource(req.fileName).getFile();

        UploadPartRequest uploadPartRequest = new UploadPartRequest()
                .withBucketName(req.bucketName)
                .withKey(req.key)
                .withUploadId(req.uploadId)
                .withPartNumber(req.partNumber)
                .withFile(file)
                .withPartSize(file.length());

        UploadPartResult uploadPartResult = s3Client.uploadPart(uploadPartRequest);
        PARTS.add(uploadPartResult.getPartETag());
    }

    @PostMapping("/complete-upload")
    public CompleteMultipartUploadResult completeUpload(@RequestBody MultiPartUploadRequest req) {
        return s3Client.completeMultipartUpload(new CompleteMultipartUploadRequest(req.bucketName, req.key, req.uploadId, PARTS));
    }

    public record UploadInitReq(String bucketName, String key) {
    }

    public record MultiPartUploadRequest(String bucketName, String key, String uploadId, int partNumber, String fileName) {
    }
}
