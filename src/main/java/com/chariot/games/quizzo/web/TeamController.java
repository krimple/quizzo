package com.chariot.games.quizzo.web;

import com.chariot.games.quizzo.model.Team;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/teams")
@Controller
@RooWebScaffold(path = "admin/teams", formBackingObject = Team.class)
public class TeamController {
}
