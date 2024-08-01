package org.sandro.msapattern.order.proxy;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import org.sandro.msapattern.order.channel.AccountingServiceChannels;
import org.sandro.msapattern.order.command.AuthorizeCommand;

public class AccountingServiceProxy {
    public final CommandEndpoint<AuthorizeCommand> authorize = CommandEndpointBuilder
            .forCommand(AuthorizeCommand.class)
            .withChannel(AccountingServiceChannels.accountingServiceChannel)
            .withReply(Success.class)
            .build();
}
