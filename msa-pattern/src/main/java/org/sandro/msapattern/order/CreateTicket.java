package org.sandro.msapattern.order;

import io.eventuate.tram.commands.CommandDestination;
import io.eventuate.tram.commands.common.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@CommandDestination("restaurantService")
public class CreateTicket implements Command {
    private Long orderId;
    private TicketDetails ticketDetails;
    private long restaurantId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}