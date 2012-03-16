package com.chariot.games.quizzo.web.ajax.response;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson(deepSerialize = true, rootName = "score")
public class CurrentScoreResponse {
    String team;
    String value;
}
