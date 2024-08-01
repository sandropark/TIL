package org.sandro.msapattern.order.command;

import io.eventuate.tram.commands.common.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sandro.msapattern.order.Money;

@Getter
@Setter
@NoArgsConstructor
public class AuthorizeCommand implements Command {
    private long consumerId;
    private Long orderId;
    private Money orderTotal;
    private Money amount;

    public AuthorizeCommand(long consumerId, Long orderId, Money orderTotal) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }
}