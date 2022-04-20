package by.pyshkodzianis.task4.action;

import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.component.CompositeText;
import by.pyshkodzianis.task4.exception.TextException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;


public class TextActionTest {
    TextAction textAction;
    @BeforeClass
    public void setUp() {
        textAction = new TextAction();
    }

    @Test
    public void testSortParagraphsBySentences(CompositeText textComposite, String expected) throws TextException {
        ComponentText actual = textAction.sortParagraphsBySentences(textComposite);
        Assert.assertEquals(actual.toString(),expected);
    }


    @Test
    public void testRemoveSentencesWithWordLessThan(CompositeText textComposite, String expected, int wordSize) throws TextException{
        ComponentText actual = textAction.removeSentencesWithWordLessThan(textComposite,wordSize);
        Assert.assertEquals(actual.toString(),expected);
    }
    @Test
    public void testFindSameWordsAndCountAmount(CompositeText textComposite) throws TextException {
        Map<String, Integer> actual = textAction.sameWordsAmount(textComposite);
        Map<String, Integer> actualButOnlyFirstSix = new LinkedHashMap<>();
        int i = 0;
        for (Map.Entry<String, Integer> entry : actual.entrySet()) {
            if (i++ < 6) {
                Integer count = entry.getValue();
                String word = entry.getKey();
                actualButOnlyFirstSix.put(word, count);
            } else {
                break;
            }
        }
        System.out.println(actual);
        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("it", 6);
        expected.put("has", 2);
        expected.put("survived", 1);
        expected.put("not", 1);
        expected.put("only", 1);
        expected.put("five", 1);
        Assert.assertEquals(actualButOnlyFirstSix, expected);
    }


    @AfterClass
    public void tearDown() {
        textAction = null;
    }
}