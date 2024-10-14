package org.sandro.s3uploadtest;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/s3")
@RestController
public class S3Controller {
    private static final String BUCKET_NAME = "";

    private final AmazonS3 s3Client;

    public S3Controller(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    // 멀티파트 업로드 요청 및 pre-signed url 생성
    @PostMapping("/multipart-upload/init")
    public UploadInitRes multipartUploadInit(@RequestBody MultipartUploadInitReq req) {
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(BUCKET_NAME, req.key);
        InitiateMultipartUploadResult initiateMultipartUploadResult = s3Client.initiateMultipartUpload(initiateMultipartUploadRequest);
        String uploadId = initiateMultipartUploadResult.getUploadId();

        List<UploadInitRes.PreSignedUrl> preSignedUrls = IntStream.range(1, req.partCount + 1)
                .mapToObj(partNumber -> {
                    GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET_NAME, req.key)
                            .withMethod(HttpMethod.PUT)
                            .withExpiration(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)));
                    generatePresignedUrlRequest.addRequestParameter("uploadId", uploadId);
                    generatePresignedUrlRequest.addRequestParameter("partNumber", String.valueOf(partNumber));
                    URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
                    return new UploadInitRes.PreSignedUrl(partNumber, url);
                }).collect(Collectors.toList());

        return new UploadInitRes(uploadId, preSignedUrls);
    }

    @PostMapping("/multipart-upload/complete")
    public URL multipartUploadComplete(@RequestBody MultiPartUploadCompleteReq req) {
        List<PartETag> partETagList = req.parts.stream()
                .map(MultiPartUploadCompleteReq.MyPartETag::toPartETag)
                .collect(Collectors.toList());
        CompleteMultipartUploadRequest request = new CompleteMultipartUploadRequest(BUCKET_NAME, req.key, req.uploadId, partETagList);
        s3Client.completeMultipartUpload(request);
        return s3Client.getUrl(BUCKET_NAME, req.key);
    }

    @PostMapping("/pre-signed-url")
    public URL presignedUrl(@RequestBody PreSignedUrlRequest req) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET_NAME, req.key)
                .withMethod(HttpMethod.PUT)
                .withExpiration(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)));
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }

    public record UploadInitRes(String uploadId, List<PreSignedUrl> preSignedUrls) {
        public record PreSignedUrl(int partNumber, URL url) {
        }
    }

    public record MultipartUploadInitReq(String key, int partCount) {
    }

    public record PreSignedUrlRequest(String key, String uploadId, int partNumber) {
    }

    public record MultiPartUploadCompleteReq(String key, String uploadId, List<MyPartETag> parts) {
        public record MyPartETag(int partNumber, String eTag) {
            public PartETag toPartETag() {
                return new PartETag(partNumber, eTag);
            }
        }
    }
}
