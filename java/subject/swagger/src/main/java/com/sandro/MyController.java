package com.sandro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Template", description = "템플릿 API Document")
@RequestMapping("/test")
@RestController
public class MyController {

    @Operation(summary = "템플릿 화면", description = "템플릿 화면을 출력합니다.", tags = {"View"})
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody InternalRequest request) {
        return ResponseEntity.ok(null);
    }

}
