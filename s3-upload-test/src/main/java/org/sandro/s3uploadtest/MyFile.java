package org.sandro.s3uploadtest;

import org.springframework.web.multipart.MultipartFile;

public record MyFile(
        String key,
        MultipartFile file
) {
}
