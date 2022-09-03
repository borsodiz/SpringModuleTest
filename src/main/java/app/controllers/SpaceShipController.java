package app.controllers;

import app.models.Crew;
import app.models.SpaceShip;
import app.models.SpaceShipClass;
import app.repositories.SpaceShipRepo;
import app.services.SpaceshipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SpaceShipController {

    @Autowired
    private SpaceshipsService spaceshipsService;

    @Autowired
    private SpaceShipRepo spaceShipRepo;



    @GetMapping(value = {"/spaceship"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());

        return "spaceship_new";
    }

    @GetMapping(value={"/spaceships"})
    public String getSpaceShipsPage (Model model) {
        List<SpaceShip> spaceShips = spaceshipsService.getAllSpaceships();

        model.addAttribute("shiplist", spaceShips);
        return "spaceships";
    }

    @GetMapping("/activeSpaceships")
    public String getActiveSpaceships (Model model) {
        List<SpaceShip> activeSpaceships = spaceshipsService.getActiveSpaceships();
        model.addAttribute("actives",activeSpaceships);

        return "activeSpaceships";
    }

    @GetMapping("/showCrew")
    public String showCrewOfSpaceship (@PathVariable long registrationCode, Model model) {

       return "crew";
    }
}
