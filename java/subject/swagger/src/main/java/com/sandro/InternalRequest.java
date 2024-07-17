package com.sandro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Schema(description = "Request")
public class InternalRequest extends ExternalRequest {

    @Schema(description = "이름") private String name;
    @Schema(description = "나이") private int age;

    public InternalRequest(String name, int age) {
        super(name, age);
    }
}
