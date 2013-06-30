package com.fly.base.process;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class OSCommand implements IOSCommand {
    private List<String> commands;

    public OSCommand(List<String> commands) {
        setCommandLines(commands);
    }

    public OSCommand(String... commands) {
        setCommandLines(commands);
    }

    @Override
    public List<String> getCommandLines() {
        return commands;
    }

    @Override
    public void setCommandLines(List<String> commandLines) {
        this.commands = commandLines;
    }

    @Override
    public void setCommandLines(String... commandLines) {
        if (commands == null) {
            commands = new ArrayList<String>();
        }

        for (String command : commandLines) {
            commands.add(command);
        }
    }
}
