package by.pyshkodzianis.task4.parser.impl;

import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.component.ComponentType;
import by.pyshkodzianis.task4.component.CompositeText;
import by.pyshkodzianis.task4.component.SymbolComponent;
import by.pyshkodzianis.task4.exception.TextException;
import by.pyshkodzianis.task4.parser.TextComponentParser;

public class LexemeParser implements TextComponentParser {
    private static final LexemeParser INSTANCE = new LexemeParser();
    public static final String WORD_CODE_REGEX = "\\w+\\.\\w+\\(.*\\)";
    public static final String PUNCTUATION_MARK_REGEX = "(\\p{Punct})";
    public static final String MARK_WORD_MARK_REGEX = "(\\p{Punct}.+\\p{Punct})";
    public static final String MARK_WORD_REGEX = "(\\p{Punct}.+)";
    public static final String CODE_MARK_REGEX = "\\w+\\.\\w+\\(.*\\)\\p{Punct}";
    public static final String WORD_MARK_REGEX = ".+\\p{Punct}";
    public static final String WORD_MARK_SPLIT = "(?=[,.!?)])";
    private LexemeParser lexemeParser = LexemeParser.getInstance();

    public LexemeParser() {
    }

    public static LexemeParser getInstance() {
        return INSTANCE;
    }
    @Override
    public ComponentText parseText(String lexeme) throws TextException {
        if (lexeme == null || lexeme.isEmpty()) {
            throw new TextException("lexeme is null or empty");
        }
        CompositeText lexemeComponent = new CompositeText(ComponentType.LEXEME);
        String word;
        SymbolComponent symbol;
        if (lexeme.matches(WORD_CODE_REGEX)) {
            ComponentText wordComponent = lexemeParser.parseText(lexeme);
            lexemeComponent.addComponent(wordComponent);
        } else if (lexeme.matches(PUNCTUATION_MARK_REGEX)) {
            symbol = new SymbolComponent(ComponentType.PUNCTUATION_MARK, lexeme.charAt(0));
            lexemeComponent.addComponent(symbol);
        } else if (lexeme.matches(MARK_WORD_MARK_REGEX)) {
            Character firstSymbol = lexeme.charAt(0);
            symbol = new SymbolComponent(ComponentType.PUNCTUATION_MARK, firstSymbol);
            lexemeComponent.addComponent(symbol);
            word = lexeme.substring(1, lexeme.length() - 1);
            ComponentText wordComponent = lexemeParser.parseText(word);
            lexemeComponent.addComponent(wordComponent);
            Character lastSymbol = lexeme.charAt(lexeme.length() - 1);
            symbol = new SymbolComponent(ComponentType.PUNCTUATION_MARK, lastSymbol);
            lexemeComponent.addComponent(symbol);
        } else if (lexeme.matches(MARK_WORD_REGEX)) {
            Character firstSymbol = lexeme.charAt(0);
            symbol = new SymbolComponent(ComponentType.PUNCTUATION_MARK, firstSymbol);
            lexemeComponent.addComponent(symbol);
            word = lexeme.substring(1, lexeme.length());
            ComponentText wordComponent = lexemeParser.parseText(word);
            lexemeComponent.addComponent(wordComponent);
        } else if (lexeme.matches(CODE_MARK_REGEX)) {
            word = lexeme.substring(0, lexeme.length() - 1);
            ComponentText wordComponent = lexemeParser.parseText(word);
            lexemeComponent.addComponent(wordComponent);
            Character lastSymbol = lexeme.charAt(lexeme.length() - 1);
            symbol = new SymbolComponent(ComponentType.PUNCTUATION_MARK, lastSymbol);
            lexemeComponent.addComponent(symbol);
        } else if (lexeme.matches(WORD_MARK_REGEX)) {
            String[] lexemeEelements = lexeme.split(WORD_MARK_SPLIT);
            for (String element : lexemeEelements) {
                if (element.matches(PUNCTUATION_MARK_REGEX)) {
                    Character markSymbol = element.charAt(0);
                    symbol = new SymbolComponent(ComponentType.PUNCTUATION_MARK, markSymbol);
                    lexemeComponent.addComponent(symbol);
                } else {
                    ComponentText wordComponent = lexemeParser.parseText(element);
                    lexemeComponent.addComponent(wordComponent);
                }
            }
        } else {
            ComponentText wordComponent = lexemeParser.parseText(lexeme);
            lexemeComponent.addComponent(wordComponent);
        }
        return lexemeComponent;
    }
}