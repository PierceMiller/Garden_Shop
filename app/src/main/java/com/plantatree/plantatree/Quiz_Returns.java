package com.plantatree.plantatree;

public class Quiz_Returns {

    public String question;
    public String choice1;
    public String choice2;
    public String choice3;
    public int correctAnswer;

    public Quiz_Returns() {}

    public Quiz_Returns(String question, String choice1, String choice2, String choice3, int correctAnswer) {
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return choice1;
    }

    public void setOption1(String option1) {
        this.choice1 = option1;
    }

    public String getOption2() {
        return choice2;
    }

    public void setOption2(String option2) {
        this.choice2 = option2;
    }

    public String getOption3() {
        return choice3;
    }

    public void setOption3(String option3) {
        this.choice3 = option3;
    }

    public int getAnswerNr() {
        return correctAnswer;
    }

    public void setAnswerNr(int answerNr) {
        this.correctAnswer = answerNr;
    }
}
