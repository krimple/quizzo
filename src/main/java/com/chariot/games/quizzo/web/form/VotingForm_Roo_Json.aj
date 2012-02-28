// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web.form;

import com.chariot.games.quizzo.web.form.VotingForm;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect VotingForm_Roo_Json {
    
    public String VotingForm.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static VotingForm VotingForm.fromJsonToVotingForm(String json) {
        return new JSONDeserializer<VotingForm>().use(null, VotingForm.class).deserialize(json);
    }
    
    public static String VotingForm.toJsonArray(Collection<VotingForm> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<VotingForm> VotingForm.fromJsonArrayToVotingForms(String json) {
        return new JSONDeserializer<List<VotingForm>>().use(null, ArrayList.class).use("values", VotingForm.class).deserialize(json);
    }
    
}
