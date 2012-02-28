package com.chariot.games.quizzo.web.form;

import java.util.ArrayList;
import java.util.List;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJson
public class TeamSetupForm {

    private String name;

    private String message;

    private List<String> teamMemberNames = new ArrayList<String>();

    public TeamSetupForm() {
        for (int i = 0; i < 4; i++) {
            teamMemberNames.add("Player " + (i + 1));
        }
    }
}
