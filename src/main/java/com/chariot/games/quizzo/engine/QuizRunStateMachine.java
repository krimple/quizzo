package com.chariot.games.quizzo.engine;

import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.QuizRunState;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface QuizRunStateMachine {

  long createQuizRun(long quizId, String text);
  void enrollTeams(long quizRunId);
  long registerTeam(Long quizRunId, String teamName, String mission);
  void startQuizRun(long quizRunId);
  boolean askNextQuestion(long quizRunId);
  void reviewAnswer(long quizRunId);
  boolean submitAnswer(long quizRunId, long teamId, long choice);
  void endQuizRun(long quizRunId);

  Map<String, BigDecimal> getScores(long quizRunId);
  Question getCurrentQuestion(long quizRunId);
  boolean hasMoreQuestions(long quizRunId);
  boolean isValidQuizRun(long quizRunId);
 // YAGNI QuizRun getQuizRun(long quizRunId);
  QuizRunState getQuizRunState(long quizRunId);
  List<QuizRun> getAllReadyQuizRuns();

  @Transactional
  BigDecimal getScoreForTeam(long teamId);

}
