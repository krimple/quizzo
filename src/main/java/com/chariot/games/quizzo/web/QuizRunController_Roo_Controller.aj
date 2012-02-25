// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.model.QuizRun;
import com.chariot.games.quizzo.model.QuizRunState;
import com.chariot.games.quizzo.service.QuizRunService;
import com.chariot.games.quizzo.service.QuizService;
import com.chariot.games.quizzo.web.QuizRunController;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect QuizRunController_Roo_Controller {
    
    @Autowired
    QuizRunService QuizRunController.quizRunService;
    
    @Autowired
    QuizService QuizRunController.quizService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String QuizRunController.create(@Valid QuizRun quizRun, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, quizRun);
            return "admin/quizruns/create";
        }
        uiModel.asMap().clear();
        quizRunService.saveQuizRun(quizRun);
        return "redirect:/admin/quizruns/" + encodeUrlPathSegment(quizRun.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String QuizRunController.createForm(Model uiModel) {
        populateEditForm(uiModel, new QuizRun());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (quizService.countAllQuizes() == 0) {
            dependencies.add(new String[] { "quiz", "quizes" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "admin/quizruns/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String QuizRunController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("quizrun", quizRunService.findQuizRun(id));
        uiModel.addAttribute("itemId", id);
        return "admin/quizruns/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String QuizRunController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("quizruns", quizRunService.findQuizRunEntries(firstResult, sizeNo));
            float nrOfPages = (float) quizRunService.countAllQuizRuns() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("quizruns", quizRunService.findAllQuizRuns());
        }
        return "admin/quizruns/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String QuizRunController.update(@Valid QuizRun quizRun, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, quizRun);
            return "admin/quizruns/update";
        }
        uiModel.asMap().clear();
        quizRunService.updateQuizRun(quizRun);
        return "redirect:/admin/quizruns/" + encodeUrlPathSegment(quizRun.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String QuizRunController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, quizRunService.findQuizRun(id));
        return "admin/quizruns/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String QuizRunController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        QuizRun quizRun = quizRunService.findQuizRun(id);
        quizRunService.deleteQuizRun(quizRun);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/admin/quizruns";
    }
    
    void QuizRunController.populateEditForm(Model uiModel, QuizRun quizRun) {
        uiModel.addAttribute("quizRun", quizRun);
        uiModel.addAttribute("quizes", quizService.findAllQuizes());
        uiModel.addAttribute("quizrunstates", Arrays.asList(QuizRunState.values()));
    }
    
    String QuizRunController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
