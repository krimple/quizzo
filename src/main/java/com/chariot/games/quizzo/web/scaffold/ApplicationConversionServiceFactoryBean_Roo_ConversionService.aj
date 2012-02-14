// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web.scaffold;

import com.chariot.games.quizzo.model.Answer;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.model.Team;
import com.chariot.games.quizzo.model.TeamMember;
import com.chariot.games.quizzo.model.Vote;
import com.chariot.games.quizzo.service.AnswerService;
import com.chariot.games.quizzo.service.QuestionService;
import com.chariot.games.quizzo.service.QuizService;
import com.chariot.games.quizzo.service.TeamMemberService;
import com.chariot.games.quizzo.service.TeamService;
import com.chariot.games.quizzo.service.VoteService;
import com.chariot.games.quizzo.web.scaffold.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    AnswerService ApplicationConversionServiceFactoryBean.answerService;
    
    @Autowired
    QuestionService ApplicationConversionServiceFactoryBean.questionService;
    
    @Autowired
    QuizService ApplicationConversionServiceFactoryBean.quizService;
    
    @Autowired
    TeamService ApplicationConversionServiceFactoryBean.teamService;
    
    @Autowired
    TeamMemberService ApplicationConversionServiceFactoryBean.teamMemberService;
    
    @Autowired
    VoteService ApplicationConversionServiceFactoryBean.voteService;
    
    public Converter<Answer, String> ApplicationConversionServiceFactoryBean.getAnswerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.chariot.games.quizzo.model.Answer, java.lang.String>() {
            public String convert(Answer answer) {
                return new StringBuilder().toString();
            }
        };
    }
    
    public Converter<Long, Answer> ApplicationConversionServiceFactoryBean.getIdToAnswerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.chariot.games.quizzo.model.Answer>() {
            public com.chariot.games.quizzo.model.Answer convert(java.lang.Long id) {
                return answerService.findAnswer(id);
            }
        };
    }
    
    public Converter<String, Answer> ApplicationConversionServiceFactoryBean.getStringToAnswerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.chariot.games.quizzo.model.Answer>() {
            public com.chariot.games.quizzo.model.Answer convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Answer.class);
            }
        };
    }
    
    public Converter<Question, String> ApplicationConversionServiceFactoryBean.getQuestionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.chariot.games.quizzo.model.Question, java.lang.String>() {
            public String convert(Question question) {
                return new StringBuilder().toString();
            }
        };
    }
    
    public Converter<Long, Question> ApplicationConversionServiceFactoryBean.getIdToQuestionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.chariot.games.quizzo.model.Question>() {
            public com.chariot.games.quizzo.model.Question convert(java.lang.Long id) {
                return questionService.findQuestion(id);
            }
        };
    }
    
    public Converter<String, Question> ApplicationConversionServiceFactoryBean.getStringToQuestionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.chariot.games.quizzo.model.Question>() {
            public com.chariot.games.quizzo.model.Question convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Question.class);
            }
        };
    }
    
    public Converter<Quiz, String> ApplicationConversionServiceFactoryBean.getQuizToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.chariot.games.quizzo.model.Quiz, java.lang.String>() {
            public String convert(Quiz quiz) {
                return new StringBuilder().toString();
            }
        };
    }
    
    public Converter<Long, Quiz> ApplicationConversionServiceFactoryBean.getIdToQuizConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.chariot.games.quizzo.model.Quiz>() {
            public com.chariot.games.quizzo.model.Quiz convert(java.lang.Long id) {
                return quizService.findQuiz(id);
            }
        };
    }
    
    public Converter<String, Quiz> ApplicationConversionServiceFactoryBean.getStringToQuizConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.chariot.games.quizzo.model.Quiz>() {
            public com.chariot.games.quizzo.model.Quiz convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Quiz.class);
            }
        };
    }
    
    public Converter<Team, String> ApplicationConversionServiceFactoryBean.getTeamToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.chariot.games.quizzo.model.Team, java.lang.String>() {
            public String convert(Team team) {
                return new StringBuilder().toString();
            }
        };
    }
    
    public Converter<Long, Team> ApplicationConversionServiceFactoryBean.getIdToTeamConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.chariot.games.quizzo.model.Team>() {
            public com.chariot.games.quizzo.model.Team convert(java.lang.Long id) {
                return teamService.findTeam(id);
            }
        };
    }
    
    public Converter<String, Team> ApplicationConversionServiceFactoryBean.getStringToTeamConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.chariot.games.quizzo.model.Team>() {
            public com.chariot.games.quizzo.model.Team convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Team.class);
            }
        };
    }
    
    public Converter<TeamMember, String> ApplicationConversionServiceFactoryBean.getTeamMemberToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.chariot.games.quizzo.model.TeamMember, java.lang.String>() {
            public String convert(TeamMember teamMember) {
                return new StringBuilder().toString();
            }
        };
    }
    
    public Converter<Long, TeamMember> ApplicationConversionServiceFactoryBean.getIdToTeamMemberConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.chariot.games.quizzo.model.TeamMember>() {
            public com.chariot.games.quizzo.model.TeamMember convert(java.lang.Long id) {
                return teamMemberService.findTeamMember(id);
            }
        };
    }
    
    public Converter<String, TeamMember> ApplicationConversionServiceFactoryBean.getStringToTeamMemberConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.chariot.games.quizzo.model.TeamMember>() {
            public com.chariot.games.quizzo.model.TeamMember convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), TeamMember.class);
            }
        };
    }
    
    public Converter<Vote, String> ApplicationConversionServiceFactoryBean.getVoteToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.chariot.games.quizzo.model.Vote, java.lang.String>() {
            public String convert(Vote vote) {
                return new StringBuilder().toString();
            }
        };
    }
    
    public Converter<Long, Vote> ApplicationConversionServiceFactoryBean.getIdToVoteConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.chariot.games.quizzo.model.Vote>() {
            public com.chariot.games.quizzo.model.Vote convert(java.lang.Long id) {
                return voteService.findVote(id);
            }
        };
    }
    
    public Converter<String, Vote> ApplicationConversionServiceFactoryBean.getStringToVoteConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.chariot.games.quizzo.model.Vote>() {
            public com.chariot.games.quizzo.model.Vote convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Vote.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getAnswerToStringConverter());
        registry.addConverter(getIdToAnswerConverter());
        registry.addConverter(getStringToAnswerConverter());
        registry.addConverter(getQuestionToStringConverter());
        registry.addConverter(getIdToQuestionConverter());
        registry.addConverter(getStringToQuestionConverter());
        registry.addConverter(getQuizToStringConverter());
        registry.addConverter(getIdToQuizConverter());
        registry.addConverter(getStringToQuizConverter());
        registry.addConverter(getTeamToStringConverter());
        registry.addConverter(getIdToTeamConverter());
        registry.addConverter(getStringToTeamConverter());
        registry.addConverter(getTeamMemberToStringConverter());
        registry.addConverter(getIdToTeamMemberConverter());
        registry.addConverter(getStringToTeamMemberConverter());
        registry.addConverter(getVoteToStringConverter());
        registry.addConverter(getIdToVoteConverter());
        registry.addConverter(getStringToVoteConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
