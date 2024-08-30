package org.sandro.msaprac.account;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sandro.msaprac.common.entity.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
public class Account extends BaseEntity {
    private String name;
    private Integer balance;
}
