package ua.hillel.patterns.chainofresponsibility;

import ua.hillel.patterns.chainofresponsibility.impl.ChainDirector;
import ua.hillel.patterns.chainofresponsibility.impl.InvalidCommandHandler;
import ua.hillel.patterns.chainofresponsibility.impl.LocalStorageHandler;
import ua.hillel.patterns.chainofresponsibility.impl.PwdHandler;

public class CORDemo {
    public static void main(String[] args) {
        ChainDirector cd = new ChainDirector(
                new PwdHandler(), new LocalStorageHandler(), new InvalidCommandHandler()
        );
        Command pwd = new Command("pwd");
        Command ls = new Command("ls");
        Command invalidCommand = new Command("ajdaia a das");

        cd.run(ls);
        cd.run(invalidCommand);
        cd.run(pwd);
    }
}
