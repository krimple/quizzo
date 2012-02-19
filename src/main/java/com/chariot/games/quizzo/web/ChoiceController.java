package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.model.Choice;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/choices")
@Controller
@RooWebScaffold(path = "admin/choices", formBackingObject = Choice.class)
public class ChoiceController {
}
