package com.chariot.games.quizzo.web.ajax.response;

import com.chariot.games.quizzo.model.Quiz;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooJson
public class QuizSelectData {
  String id;
  String value;

  public QuizSelectData() {
    super();
  }

  public QuizSelectData(String id, String value) {
    this.id = id;
    this.value = value;
  }

  public static List<QuizSelectData> convert(List<Quiz> quizList) {
    List<QuizSelectData> retValues = new ArrayList<QuizSelectData>();
    for (Quiz quiz : quizList) {
      QuizSelectData selectData = new QuizSelectData();

      selectData.setId(String.valueOf(quiz.getId()));
      selectData.setValue(quiz.getTitle());
      retValues.add(selectData);
    }
    return retValues;
  }
}
