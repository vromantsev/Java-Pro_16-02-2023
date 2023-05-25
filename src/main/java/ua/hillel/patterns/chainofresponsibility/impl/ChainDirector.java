package ua.hillel.patterns.chainofresponsibility.impl;

import ua.hillel.patterns.chainofresponsibility.Command;
import ua.hillel.patterns.chainofresponsibility.CommandExecutionResult;
import ua.hillel.patterns.chainofresponsibility.CommandLineHandler;

public class ChainDirector {

    private CommandLineHandler pwdHandler;
    private CommandLineHandler localStorageHandler;
    private CommandLineHandler unknownCommandHandler;

    public ChainDirector(CommandLineHandler pwdHandler, CommandLineHandler localStorageHandler, CommandLineHandler unknownCommandHandler) {
        this.pwdHandler = pwdHandler;
        this.localStorageHandler = localStorageHandler;
        this.unknownCommandHandler = unknownCommandHandler;
        pwdHandler // 1
                .link(localStorageHandler) // 2
                .link(unknownCommandHandler); // 3
    }

    public CommandExecutionResult run(final Command command) {
        return pwdHandler.process(command);
    }
}
