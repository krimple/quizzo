package com.chariot.games.quizzo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Question.class)
public class QuestionDataOnDemand {

    @Autowired
    private QuizDataOnDemand quizDataOnDemand;

    public Question getNewTransientQuestion(int index) {
        Question obj = new Question();
        setQuestionText(obj, index);
        setQuiz(obj, index);
        return obj;
    }

    public void setQuiz(Question obj, int index) {
        if (quizDataOnDemand == null) {
            quizDataOnDemand = new QuizDataOnDemand();
            quizDataOnDemand.init();
        }

        Quiz quiz =quizDataOnDemand.getRandomQuiz();
        obj.setQuiz(quiz);
    }
}
