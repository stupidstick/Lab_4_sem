package controller;

import data.Parameters;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.regex.Pattern;

public class ConsoleController {
    @FXML
    TextArea consoleArea;
    InputStream inputStream;
    OutputStream outputStream;
    PrintStream printStream;

    private String savedText = "";
    private String inputText = "";
    private int savedPosition = 0;

    @FXML
    public void inputEvent(KeyEvent keyEvent) {
        if (consoleArea.getCaretPosition() < savedPosition){
            consoleArea.setText(savedText + inputText);
            consoleArea.positionCaret(savedPosition);
        }

        if (keyEvent.getCode() == KeyCode.ENTER) {
            PrintStream standardStream = System.out;
            getCommand(getLine());
            Parameters.consoleDispatcher();
            consoleArea.insertText(consoleArea.getText().length(), outputStream.toString());
            System.setOut(standardStream);
            savedPosition = consoleArea.getCaretPosition();
            savedText = consoleArea.getText();
            inputText = "";
        }

        Pattern pattern = Pattern.compile(savedText + ".*");
        if (pattern.matcher(consoleArea.getText()).matches()){
            inputText = consoleArea.getText().replace(savedText, "");
        }
    }

    private String getLine() {
        String[] lines = consoleArea.getText().split("\n");
        return (lines.length == 0) ? "" : lines[lines.length - 1];
    }

    public void getCommand(String command) {
        inputStream = new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);

        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);

    }

}
