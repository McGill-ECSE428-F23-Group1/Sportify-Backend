package ca.mcgill.ecse428.group1.sportifybackend.dao;

import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import ca.mcgill.ecse428.group1.sportifybackend.model.Sport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SpecificSportRepository extends CrudRepository<SpecificSport, String> {

    SpecificSport findById(long id);

    List<SpecificSport> findBySport(Sport sport);

}