package com.winash.uniapp.ui.FAQ;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class FAQ_questionTest extends TestCase {
    FAQ_question faq = new FAQ_question();
    @Test
    public void testGetQuestion() {
        //Test whether the initialization is set to NULL
        assertNull(faq.getQuestion());
        //Check equivalence for the updated object parameter
        faq.question="What to do?";
        assertEquals("What to do?",faq.getQuestion());
    }
    @Test
    public void testNotGetQuestion() {
        faq.question="What to do?";
        Assert.assertNotEquals("what to do?",faq.getQuestion());
    }
    @Test
    public void testGetAnswer() {
        //Test whether the initialization is set to NULL
        assertNull(faq.getAnswer());
        //Check equivalence for the updated object parameter
        faq.answer="Have fun!";
        assertEquals("Have fun!",faq.getAnswer());
    }
    @Test
    public void testIsExpandable() {
        //Test whether the initialized value is set to false
        assertFalse(faq.isExpandable());
    }
    @Test
    public void testNotIsExpandable() {
        assertTrue(!faq.isExpandable());
    }
    @Test
    public void testSetExpandable() {
        //Update value and then test
        faq.setExpandable(true);
        assertTrue(faq.isExpandable());
    }
}