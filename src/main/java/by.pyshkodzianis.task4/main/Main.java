package by.pyshkodzianis.task4.main;

import by.pyshkodzianis.task4.action.TextAction;
import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.component.CompositeText;
import by.pyshkodzianis.task4.exception.TextException;
import by.pyshkodzianis.task4.parser.impl.TextParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static final String PATH = ClassLoader.getSystemResource("text.txt").getPath();

    public static void main(String[] args) throws IOException, TextException {

        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine()).append("\n");
            }
        }
        ComponentText compositeText = new TextParser().parseText(text.toString());
        TextAction service = new TextAction();
        service.sortParagraphs(compositeText);
    }
}