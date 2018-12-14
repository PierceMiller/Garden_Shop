package com.plantatree.plantatree;

import android.provider.BaseColumns;

public final class Quiz_Contract {

    public Quiz_Contract() {} //precaution

    public static class QuestionsTable implements BaseColumns{

        public static final String QUIZ_TABLE = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_CHOICE1 = "option1";
        public static final String COLUMN_CHOICE2 = "option2";
        public static final String COLUMN_CHOICE3 = "option3";
        public static final String COLUMN_CORRECT_ANSWER = "answer_nr";
    }
}

