package com.chariot.games.quizzo.web.scaffold;

import com.chariot.games.quizzo.model.Team;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/teams")
@Controller
@RooWebScaffold(path = "teams", formBackingObject = Team.class)
public class TeamController {
}
