package app.services;

import app.helpers.DataLoader;
import app.models.SpaceShip;
import app.repositories.SpaceShipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpaceshipsService {

    private SpaceShipRepo spaceShipRepo;

    private DataLoader dataLoader;

    @Autowired
    public SpaceshipsService(SpaceShipRepo spaceShipRepo, DataLoader dataLoader) {
        this.spaceShipRepo = spaceShipRepo;
        this.dataLoader = dataLoader;
    }


    public List<SpaceShip> getAllSpaceships(){
        List<SpaceShip> spaceShips = (List<SpaceShip>) spaceShipRepo.findAll();
        return spaceShips;
    }

    public List<SpaceShip> getActiveSpaceships() {
        List<SpaceShip> activeSpaceships = new ArrayList<>();
        for (SpaceShip spaceShip : getAllSpaceships()){
            if (spaceShip.isActive()){
                activeSpaceships.add(spaceShip);
            }
        }
        return activeSpaceships;
    }

}
