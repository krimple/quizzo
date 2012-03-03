package com.chariot.games.quizzo.web.ajax.response;

import com.chariot.games.quizzo.model.QuizRun;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooSerializable
@RooJson
public class QuizRunSelectData {
  private Long id;
  private String label;

  public static List<QuizRunSelectData> convert(List<QuizRun> quizRunList) {
    List<QuizRunSelectData> retValues = new ArrayList<QuizRunSelectData>();
    for (QuizRun quizRun : quizRunList) {
      QuizRunSelectData selectData = new QuizRunSelectData();
      selectData.setId(quizRun.getId());
      selectData.setLabel(quizRun.getQuiz().getTitle() + " - " + quizRun.getText());
      retValues.add(selectData);
    }
    return retValues;
  }
}
