package gg.raf.suite.command;

import gg.raf.suite.command.functions.CommandFunction;
import gg.raf.suite.command.functions.Function;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 */
public enum Command {

    PULL("pull", new CommandFunction() {
        public void execute() {

        }
    }),
    PUSH("push", new CommandFunction() {
       public void execute() {

       }
    }
    );

    private String name;
    private Function function;

    Command(String name, Function function) {
        this.name = name;
        this.function = function;
    }

}
