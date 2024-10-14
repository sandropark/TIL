package net.com.sandro;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ExternalOrderCreateReq {
    protected Float price;
}
