// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web.ajax.response;

import com.chariot.games.quizzo.web.ajax.response.QuestionChoiceResponse;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect QuestionChoiceResponse_Roo_Json {
    
    public String QuestionChoiceResponse.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static QuestionChoiceResponse QuestionChoiceResponse.fromJsonToQuestionChoiceResponse(String json) {
        return new JSONDeserializer<QuestionChoiceResponse>().use(null, QuestionChoiceResponse.class).deserialize(json);
    }
    
    public static String QuestionChoiceResponse.toJsonArray(Collection<QuestionChoiceResponse> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<QuestionChoiceResponse> QuestionChoiceResponse.fromJsonArrayToQuestionChoiceResponses(String json) {
        return new JSONDeserializer<List<QuestionChoiceResponse>>().use(null, ArrayList.class).use("values", QuestionChoiceResponse.class).deserialize(json);
    }
    
}