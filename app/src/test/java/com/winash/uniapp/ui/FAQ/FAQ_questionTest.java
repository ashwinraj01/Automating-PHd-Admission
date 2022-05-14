package com.winash.uniapp.ui.FAQ;

import junit.framework.TestCase;

public class FAQ_questionTest extends TestCase {
    FAQ_question faq = new FAQ_question();
    public void testGetQuestion() {
        //Test whether the initialization is set to NULL
        assertNull(faq.getQuestion());
        //Check equivalence for the updated object parameter
        faq.question="What to do?";
        assertEquals("What to do?",faq.getQuestion());
    }

    public void testGetAnswer() {
        //Test whether the initialization is set to NULL
        assertNull(faq.getAnswer());
        //Check equivalence for the updated object parameter
        faq.answer="Have fun!";
        assertEquals("Have fun!",faq.getAnswer());
    }

    public void testIsExpandable() {
        //Test whether the initialized value is set to false
        assertFalse(faq.isExpandable());
    }

    public void testSetExpandable() {
        //Update value and then test
        faq.setExpandable(true);
        assertTrue(faq.isExpandable());
    }
}