package data;

import java.io.Serializable;

public class CommandDTO implements Serializable {
    private String command;
    private Object value;
    public CommandDTO(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
