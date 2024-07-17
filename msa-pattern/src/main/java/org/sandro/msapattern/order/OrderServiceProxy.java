package org.sandro.msapattern.order;

import io.eventuate.tram.commands.consumer.CommandWithDestination;

import java.util.function.Function;

public class OrderServiceProxy {
    public Function<CreateOrderSagaState, CommandWithDestination> reject;
}
