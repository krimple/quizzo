package com.chariot.games.quizzo.web;

import javax.servlet.http.HttpSession;

public class QuizWebSessionUtils {
  public QuizWebSessionUtils() {
  }

  public static Long getQuizRunIdFromSession(HttpSession session, boolean required) {
    Long quizRunId = (Long) session.getAttribute("currentQuizRunId");
    if (required && quizRunId == null) {
      throw new IllegalStateException("Quiz Run not configured for user.");
    }
    return quizRunId;
  }

  public static Long getCurrentTeamIdFromSession(HttpSession session, boolean required) {
      Long teamId = (Long) session.getAttribute("currentTeamId");
      if (required && teamId == null) {
        throw new IllegalStateException("Team not configured for user.");
      }
    return teamId;
  }

  public static void setQuizRunIdForSession(HttpSession session, long quizRunId) {
    session.setAttribute("currentQuizRunId", quizRunId);
  }
}