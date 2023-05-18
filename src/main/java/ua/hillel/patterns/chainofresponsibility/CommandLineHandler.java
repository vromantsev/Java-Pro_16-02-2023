package ua.hillel.patterns.chainofresponsibility;

import java.util.logging.Logger;

public abstract class CommandLineHandler {

    protected static final Logger LOGGER = Logger.getLogger(CommandLineHandler.class.getName());

    private CommandLineHandler next;

    public CommandLineHandler link(final CommandLineHandler commandLineHandler) {
        this.next = commandLineHandler;
        return commandLineHandler;
    }

    public abstract CommandExecutionResult process(final Command command);

    public CommandExecutionResult processNext(final Command command) {
        return next.process(command);
    }
}
