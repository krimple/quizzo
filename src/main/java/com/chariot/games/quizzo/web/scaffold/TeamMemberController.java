package com.chariot.games.quizzo.web.scaffold;

import com.chariot.games.quizzo.model.TeamMember;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/teammembers")
@Controller
@RooWebScaffold(path = "teammembers", formBackingObject = TeamMember.class)
public class TeamMemberController {
}
