package com.chariot.games.quizzo.web.ajax.request;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;

@RooJavaBean
@RooSerializable
@RooToString
@RooJson
public class VotingForm {

    @NotNull
    private Long choiceId;

}
