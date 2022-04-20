package by.pyshkodzianis.task4.service;

import by.pyshkodzianis.task4.component.ComponentText;
import by.pyshkodzianis.task4.exception.TextException;
import by.pyshkodzianis.task4.parser.impl.TextParser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParserImplTests {
    private TextParser textParser;

    @BeforeClass
    public void setUp() {
        textParser = TextParser.getInstance();
    }

    @Test
    public void textParserTest(ComponentText expected, String text) throws TextException {
        ComponentText actual = textParser.parseText(text);
        Assert.assertEquals(actual.toString(),expected.toString());
    }
    @AfterClass
    public void tearDown() {
        textParser = null;
    }
}

