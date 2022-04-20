package by.pyshkodzianis.task4.parser.impl;

import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.component.ComponentType;
import by.pyshkodzianis.task4.component.CompositeText;
import by.pyshkodzianis.task4.component.SymbolComponent;
import by.pyshkodzianis.task4.exception.TextException;
import by.pyshkodzianis.task4.parser.TextComponentParser;


public class WordParser implements TextComponentParser{
    private static final WordParser INSTANCE = new WordParser();

    private WordParser(){}

    public static WordParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ComponentText parseText(String word) throws TextException {
        CompositeText wordComponent = new CompositeText(ComponentType.WORD);
        char[] symbols = word.toCharArray();
        SymbolComponent symbolComponent;
        for (char symbol : symbols) {
            symbolComponent = new SymbolComponent(ComponentType.SYMBOL, symbol);
            wordComponent.addComponent(symbolComponent);
        }
        return wordComponent;
    }


}


