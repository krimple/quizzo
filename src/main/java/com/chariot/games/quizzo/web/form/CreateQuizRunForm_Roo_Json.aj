// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web.form;

import com.chariot.games.quizzo.web.form.CreateQuizRunForm;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect CreateQuizRunForm_Roo_Json {
    
    public String CreateQuizRunForm.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static CreateQuizRunForm CreateQuizRunForm.fromJsonToCreateQuizRunForm(String json) {
        return new JSONDeserializer<CreateQuizRunForm>().use(null, CreateQuizRunForm.class).deserialize(json);
    }
    
    public static String CreateQuizRunForm.toJsonArray(Collection<CreateQuizRunForm> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<CreateQuizRunForm> CreateQuizRunForm.fromJsonArrayToCreateQuizRunForms(String json) {
        return new JSONDeserializer<List<CreateQuizRunForm>>().use(null, ArrayList.class).use("values", CreateQuizRunForm.class).deserialize(json);
    }
    
}
