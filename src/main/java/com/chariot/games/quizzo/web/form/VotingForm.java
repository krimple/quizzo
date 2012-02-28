package com.chariot.games.quizzo.web.form;

import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooSerializable
@RooToString
@RooJson
public class VotingForm {

    @NotNull
    private Long questionId;

    @NotNull
    private Long choiceId;

    private Boolean selected;
}
