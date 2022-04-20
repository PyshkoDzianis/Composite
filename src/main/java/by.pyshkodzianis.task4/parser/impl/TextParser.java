package by.pyshkodzianis.task4.parser.impl;


import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.component.ComponentType;
import by.pyshkodzianis.task4.component.CompositeText;
import by.pyshkodzianis.task4.exception.TextException;
import by.pyshkodzianis.task4.parser.TextComponentParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser implements TextComponentParser {
    public static Logger logger = LogManager.getLogger();
    private static final TextParser INSTANCE = new TextParser();
    public static final String PARAGRAPH_SPLIT = "\s{4}";
    private ParagraphParser paragraphParser = ParagraphParser.getInstance();

    public TextParser(){

    }
    public static TextParser getInstance(){
        return INSTANCE;
    }
    @Override
    public ComponentText parseText(String text) throws TextException {
        if (text == null || text.isEmpty()) {
            throw new TextException("text is null or empty");
        }
        CompositeText textComponent = new CompositeText(ComponentType.TEXT);
        String[] paragraphData = text.split(PARAGRAPH_SPLIT);
        for (String paragraph : paragraphData) {
            if (!paragraph.isEmpty()) {
                ComponentText paragraphComponent = paragraphParser.parseText(paragraph);
                textComponent.addComponent(paragraphComponent);
            }
        }
        logger.log(Level.INFO, "after parsing the component text is created: " + textComponent.toString());
        return textComponent;
    }


}