package app.controllers;

import app.models.Crew;
import app.services.CrewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CrewController {

    private CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }


    @GetMapping(value={"/crew"})
    public String getCrewPage(Model model) {
        List<Crew> crewList = crewService.getCrewMembers();
        model.addAttribute("crewMembers", crewList);
        return "crew";
    }
}
