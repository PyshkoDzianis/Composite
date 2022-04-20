package by.pyshkodzianis.task4.parser.impl;

import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.component.ComponentType;
import by.pyshkodzianis.task4.component.CompositeText;
import by.pyshkodzianis.task4.exception.TextException;
import by.pyshkodzianis.task4.parser.TextComponentParser;

public class ParagraphParser implements TextComponentParser {
    private static final ParagraphParser INSTANCE = new ParagraphParser();
    public static final String PARAGRAPH_SPLIT = "(?=(\\t))";
    private SentenceParser sentenceParser = SentenceParser.getInstance();

    private ParagraphParser(){}

    public static ParagraphParser getInstance() {
        return INSTANCE;
    }


    @Override
    public ComponentText parseText(String paragraph) throws TextException {
        if (paragraph == null || paragraph.isEmpty()) {
            throw new TextException("paragraph is null or empty");
        }
        CompositeText paragraphComponent = new CompositeText(ComponentType.PARAGRAPH);
        String[] paragraphs = paragraph.split(PARAGRAPH_SPLIT);
        for (String sentence : paragraphs) {
            ComponentText sentenceComponent = sentenceParser.parseText(sentence);
            paragraphComponent.addComponent(sentenceComponent);
        }
        return paragraphComponent;
    }
}