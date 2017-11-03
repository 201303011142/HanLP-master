package com.hankcs.test.corpus;

import java.util.Map;

import com.hankcs.hanlp.corpus.dictionary.DictionaryMaker;
import com.hankcs.hanlp.corpus.dictionary.PlaceSuffixDictionary;
import com.hankcs.hanlp.corpus.dictionary.SuffixDictionary;
import com.hankcs.hanlp.corpus.tag.NS;
import com.hankcs.hanlp.utility.Predefine;

import junit.framework.TestCase;

public class SuffixDictionaryTest extends TestCase
{
    SuffixDictionary dictionary = new SuffixDictionary();
    public void setUp() throws Exception
    {
        super.setUp();
        dictionary.addAll(Predefine.POSTFIX_SINGLE);
        dictionary.addAll(Predefine.POSTFIX_MUTIPLE);
    }

    public void testGet() throws Exception
    {
        String total = Predefine.POSTFIX_SINGLE;
        for (int i = 0; i < total.length(); ++i)
        {
            String single = String.valueOf(total.charAt(i));
            assertEquals(1, dictionary.get(single));
        }
        for (String single : Predefine.POSTFIX_MUTIPLE)
        {
            assertEquals(single.length(), dictionary.get(single));
        }
    }

    public void testEndsWith() throws Exception
    {
        assertEquals(true, dictionary.endsWith("黄冈市"));
        assertEquals(false, dictionary.endsWith("黄冈一二三"));
    }

    public void testLongest() throws Exception
    {
        assertEquals(2, dictionary.getLongestSuffixLength("巴尔干半岛"));
    }

    public void testDump() throws Exception
    {
        DictionaryMaker dictionaryMaker = new DictionaryMaker();
        for (Map.Entry<String, Integer> entry : PlaceSuffixDictionary.dictionary.entrySet())
        {
            dictionaryMaker.add(entry.getKey(), NS.H.toString());
        }
        dictionaryMaker.saveTxtTo("data/dictionary/place/suffix.txt");
    }
}