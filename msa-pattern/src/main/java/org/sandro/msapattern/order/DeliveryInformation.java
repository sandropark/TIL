package org.sandro.msapattern.order;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Access(AccessType.FIELD)
public class DeliveryInformation {
  private LocalDateTime deliveryTime;
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name="state", column=@Column(name="delivery_state"))
  })
  private Address deliveryAddress;
}