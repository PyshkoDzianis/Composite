package by.pyshkodzianis.task4.parser;

import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.exception.TextException;


public interface TextComponentParser {
    ComponentText parseText(String text) throws TextException;
}