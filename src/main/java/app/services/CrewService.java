package app.services;


import app.helpers.DataLoader;
import app.models.Crew;
import app.repositories.CrewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewService {

    private CrewRepo crewRepo;

    private DataLoader dataLoader;


    @Autowired
    public CrewService(CrewRepo crewRepo, DataLoader dataLoader) {
        this.crewRepo = crewRepo;
        this.dataLoader = dataLoader;
    }

    public List<Crew> getCrewMembers() {
        List<Crew> crewList = (List<Crew>) crewRepo.findAll();
        return crewList;
    }

}
