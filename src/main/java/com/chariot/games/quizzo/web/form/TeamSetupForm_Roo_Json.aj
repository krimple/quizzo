// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web.form;

import com.chariot.games.quizzo.web.form.TeamSetupForm;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect TeamSetupForm_Roo_Json {
    
    public String TeamSetupForm.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static TeamSetupForm TeamSetupForm.fromJsonToTeamSetupForm(String json) {
        return new JSONDeserializer<TeamSetupForm>().use(null, TeamSetupForm.class).deserialize(json);
    }
    
    public static String TeamSetupForm.toJsonArray(Collection<TeamSetupForm> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<TeamSetupForm> TeamSetupForm.fromJsonArrayToTeamSetupForms(String json) {
        return new JSONDeserializer<List<TeamSetupForm>>().use(null, ArrayList.class).use("values", TeamSetupForm.class).deserialize(json);
    }
    
}
