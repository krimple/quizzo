package com.chariot.games.quizzo.service;


import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.Team;

import java.util.List;

public class AnswerServiceBean implements AnswerService {
  @Override
  public List<Answer> getAnswersByTeamIdAndQuestionId(Long teamId, Long questionId) {
    return answerRepository.getAnswersByTeamIdAndQuestionId(teamId, questionId);
  }

  public void replaceAnswers(Team team, Question question, Choice choice) {
    answerRepository.deleteAllAnswersByQuestionIdAndTeamId(
        choice.getQuestion().getId(), team.getId());
    Answer answer = new Answer();
    answer.setChoice(choice);
    answer.setTeam(team);
    saveAnswer(answer);
  }
}
