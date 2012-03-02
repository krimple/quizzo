package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.QuizRunState;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

public interface QuizRunStateMachine {

  long createQuizRun(long quizId, String text);
  void enrollTeams(long quizRunId);
  void startQuizRun(long quizRunId);
  boolean nextQuestion(long quizRunId);
  Map<String, BigDecimal> getScores(long quizRunId);
  Question getCurrentQuestion(long quizRunId);
  boolean hasMoreQuestions(long quizRunId);
  boolean submitAnswer(long quizRunId, long teamId, long choice);
  void endQuizRun(long quizRunId);
  long registerTeam(Long quizRunId, String teamName, String mission);
 // YAGNI QuizRun getQuizRun(long quizRunId);
  QuizRunState getQuizRunState(long quizRunId);

  @Transactional
  BigDecimal getScoreForTeam(long teamId);

}
