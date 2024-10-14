package net.com.sandro;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ExternalOrderCreateRes {
    protected Float price;
}
