// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web.ajax.response;

import com.chariot.games.quizzo.web.ajax.response.QuizRunSelectData;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect QuizRunSelectData_Roo_Json {
    
    public String QuizRunSelectData.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static QuizRunSelectData QuizRunSelectData.fromJsonToQuizRunSelectData(String json) {
        return new JSONDeserializer<QuizRunSelectData>().use(null, QuizRunSelectData.class).deserialize(json);
    }
    
    public static String QuizRunSelectData.toJsonArray(Collection<QuizRunSelectData> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<QuizRunSelectData> QuizRunSelectData.fromJsonArrayToQuizRunSelectDatas(String json) {
        return new JSONDeserializer<List<QuizRunSelectData>>().use(null, ArrayList.class).use("values", QuizRunSelectData.class).deserialize(json);
    }
    
}
