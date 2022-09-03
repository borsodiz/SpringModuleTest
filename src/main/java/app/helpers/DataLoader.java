package app.helpers;

import app.models.Crew;
import app.models.SpaceShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * DO NOT TOUCH!
 *
 * This class is responsible for saving the test data.
 */
@Component
public class DataLoader implements ApplicationRunner {

    @PersistenceContext
    private EntityManager em;

    private final ShipData shipData;


    public DataLoader(ShipData shipData) {
        this.shipData = shipData;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        if (countShips() == 0) {
            getAndSaveShips();
            getAndSaveCrews();
        }
    }

    long countShips() {
        return em.createQuery("SELECT s FROM SpaceShip s", SpaceShip.class)
                .getResultList()
                .size();
    }

    void getAndSaveShips() {
        saveShips(
                shipData.titanicShip(),
                shipData.sevenUpShip(),
                shipData.babyDollShip(),

                shipData.woodenHorseShip(),
                shipData.waveRiderShip(),
                shipData.nautilusShip(),

                shipData.blackPearlShip(),
                shipData.stormrageShip(),
                shipData.pequodShip(),

                shipData.theCubeShip(),
                shipData.enterpriseShip(),
                shipData.theOakShip(),
                shipData.tardisShip(),

                shipData.rexShip(),
                shipData.theEngineerShip()
        );
    }


    void saveShips(SpaceShip... ships) {
        for (SpaceShip ship : ships) {
            em.persist(ship);
        }
    }

    void getAndSaveCrews() {
        findShipAndSaveCrew(shipData.titanic(), shipData.titanicCrews());
        findShipAndSaveCrew(shipData.sevenUp(), shipData.sevenUpCrews());
        findShipAndSaveCrew(shipData.woodenHorse(), shipData.woodenHorseCrews());
        findShipAndSaveCrew(shipData.waveRider(), shipData.waveRiderCrews());
        findShipAndSaveCrew(shipData.nautilus(), shipData.nautilusCrews());
        findShipAndSaveCrew(shipData.blackPearl(), shipData.blackPearlCrews());
        findShipAndSaveCrew(shipData.stormrage(), shipData.stormrageCrews());
        findShipAndSaveCrew(shipData.pequod(), shipData.pequodCrews());
        findShipAndSaveCrew(shipData.theCube(), shipData.theCubeCrews());
        findShipAndSaveCrew(shipData.enterprise(), shipData.enterpriseCrews());
        findShipAndSaveCrew(shipData.theOak(), shipData.theOakCrews());
        findShipAndSaveCrew(shipData.theEngineer(), shipData.theEngineerCrew());
        findShipAndSaveCrew(shipData.rex(), shipData.rexCrew());
        findShipAndSaveCrew(shipData.tardis(), shipData.tardisCrews());
    }

    void findShipAndSaveCrew(String shipName, Crew... crews) {
        SpaceShip ship = findShip(shipName);

        if (ship != null) {
            for (Crew crew : crews) {
                crew.setDutyShip(ship);
                em.persist(crew);
            }

            ship.setCrews(Arrays.asList(crews));
        }
    }

    SpaceShip findShip(String shipName) {
        try {
            return em.createQuery("SELECT s FROM SpaceShip s WHERE s.name = :name", SpaceShip.class)
                    .setParameter("name", shipName)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}