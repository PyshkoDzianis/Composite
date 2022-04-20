package by.pyshkodzianis.task4.action;

import by.pyshkodzianis.task4.comparator.TextComparator;
import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.component.ComponentType;
import by.pyshkodzianis.task4.component.CompositeText;
import by.pyshkodzianis.task4.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.*;
import java.util.List;

public class TextAction {
    public static final int WORD_COUNT_ONE = 1;
    private static Logger logger = LogManager.getLogger();

    public boolean sortParagraphs(ComponentText component) {
        if (component.getComponentType() == ComponentType.TEXT) {
            component.getComponents().sort(Comparator.comparingInt(o -> o.getComponents().size()));
        } else return false;
        return true;
    }

    public ComponentText sortParagraphsBySentences(CompositeText text) throws TextException {
        if (text == null || text.getType() != ComponentType.TEXT) {
            throw new TextException("component is invalid");
        }
        ComponentText sortedText = new CompositeText(ComponentType.PARAGRAPH);
        List<ComponentText> paragraphs = new ArrayList<>(text.getComponents());
        paragraphs.sort(new TextComparator());
        for (ComponentText paragraph : paragraphs) {
            sortedText.addComponent(paragraph);
        }
        return text;
    }


    public ComponentText findLongestWordSentence(ComponentText text) throws TextException {
        if (text == null || text.getType() != ComponentType.TEXT) {
            throw new TextException("component is invalid");
        }
        ComponentText longestWordSentence = new CompositeText(ComponentType.WORD);
        List<ComponentText> paragraphList = text.getComponents();
        int maxLength = 0;
        for (ComponentText text1 : paragraphList) {
            List<ComponentText> wordList = text1.getComponents();
            for (ComponentText text2 : wordList) {
                if (text2.getType() == ComponentType.WORD) {
                    if (text2.getComponents().size() > maxLength) {
                        maxLength = text2.getComponents().size();
                        longestWordSentence = text2;
                    }

                }
            }
        }
        return longestWordSentence;
    }


    public ComponentText removeSentencesWithWordLessThan(ComponentText text, int sentenceSize) throws TextException {
        if (text == null || text.getType() != ComponentType.TEXT) {
            throw new TextException("component is invalid");
        }
        List<ComponentText> removeSentences = new ArrayList<>();
        for (ComponentText paragraph : text.getComponents()) {
            for (ComponentText sentence : paragraph.getComponents()) {
                int numberWords = 0;
                for (ComponentText lexem : sentence.getComponents()) {
                    for (ComponentText part : lexem.getComponents()) {
                        if (part.getType() == ComponentType.WORD) {
                            numberWords++;
                        }
                    }
                }
                if (numberWords < sentenceSize) {
                    removeSentences.add(sentence);
                }
            }
        }
        for (ComponentText paragraph : text.getComponents()) {
            List<ComponentText> sentences = paragraph.getComponents();
            List<ComponentText> sentencesCopy = new ArrayList<>(sentences);
            sentencesCopy.removeAll(removeSentences);
            ((CompositeText) paragraph).setComponents(sentencesCopy);
        }
        return text;
    }

    public Map<String, Integer> sameWordsAmount(CompositeText text) throws TextException {
        if (text == null || text.getType() != ComponentType.TEXT) {
            throw new TextException("component is invalid");
        }
        Map<String, Integer> identicalWords = new LinkedHashMap<>();
        for (ComponentText paragraph : text.getComponents()) {
            for (ComponentText sentence : paragraph.getComponents()) {
               for (ComponentText lexeme : sentence.getComponents()) {
                   for (ComponentText elementLexeme : lexeme.getComponents()) {
                       if (elementLexeme.getType() == ComponentType.WORD) {
                           String word = elementLexeme.toString().toLowerCase();
                           if (!identicalWords.containsKey(word)) {
                               identicalWords.put(word, 1);
                           } else {
                                int count = identicalWords.get(word);
                                identicalWords.put(word, WORD_COUNT_ONE);
                            }
                        }
                    }
                }
            }
        }
        Iterator<Map.Entry<String, Integer>> iterator = identicalWords.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> wordAndNumber = iterator.next();
            Integer number = wordAndNumber.getValue();
            if (number == WORD_COUNT_ONE) {
                iterator.remove();
            }
        }
        logger.log(Level.INFO, "identical words in the text and their number: " + identicalWords.toString());
        return identicalWords;
    }

}