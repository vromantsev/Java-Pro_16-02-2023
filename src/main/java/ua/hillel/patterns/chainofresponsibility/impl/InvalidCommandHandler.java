package ua.hillel.patterns.chainofresponsibility.impl;

import ua.hillel.patterns.chainofresponsibility.Command;
import ua.hillel.patterns.chainofresponsibility.CommandExecutionResult;
import ua.hillel.patterns.chainofresponsibility.CommandLineHandler;

public class InvalidCommandHandler extends CommandLineHandler {

    @Override
    public CommandExecutionResult process(Command command) {
        LOGGER.warning("Unknown command %s".formatted(command.getCommand()));
        return new CommandExecutionResult(false, "failure");
    }
}
