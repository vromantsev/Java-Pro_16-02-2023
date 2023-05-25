package ua.hillel.patterns.chainofresponsibility.impl;

import ua.hillel.patterns.chainofresponsibility.Command;
import ua.hillel.patterns.chainofresponsibility.CommandExecutionResult;
import ua.hillel.patterns.chainofresponsibility.CommandLineHandler;

public class PwdHandler extends CommandLineHandler {

    @Override
    public CommandExecutionResult process(Command command) {
        if (command.getCommand().equals("pwd")) {
            LOGGER.info("Command 'pwd' was executed");
            return new CommandExecutionResult(true, "success");
        }
        return super.processNext(command);
    }
}
