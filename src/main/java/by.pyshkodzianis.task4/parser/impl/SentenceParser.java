package by.pyshkodzianis.task4.parser.impl;

import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.component.ComponentType;
import by.pyshkodzianis.task4.component.CompositeText;
import by.pyshkodzianis.task4.exception.TextException;
import by.pyshkodzianis.task4.parser.TextComponentParser;


public class SentenceParser implements TextComponentParser {
    private static final SentenceParser INSTANCE = new SentenceParser();
    private static final String SENTENCE_SPLIT = "\s";
    private WordParser wordParser = WordParser.getInstance();

    private SentenceParser(){

    }
    public static SentenceParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ComponentText parseText(String sentence) throws TextException {
        if (sentence == null || sentence.isEmpty()) {
            throw new TextException("sentence is null or empty");
        }
        CompositeText sentenceComponent = new CompositeText(ComponentType.SENTENCE);
        String[] words = sentence.split(SENTENCE_SPLIT);
        for (String word : words) {
            ComponentText wordComponent = wordParser.parseText(word);
            sentenceComponent.addComponent(wordComponent);
        }
        return sentenceComponent;
    }
}