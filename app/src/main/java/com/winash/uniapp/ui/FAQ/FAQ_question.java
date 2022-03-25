package com.winash.uniapp.ui.FAQ;

public class FAQ_question {
    public String question,answer;
    public boolean expandable;
    public FAQ_question() {
        this.question =null;
        this.answer = null;
        expandable=false;
    }
    public FAQ_question(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.expandable=false;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }


    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
