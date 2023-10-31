package ca.mcgill.ecse428.group1.sportifybackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.group1.sportifybackend.model.Sport;

import java.util.List;

public interface SportRepository extends CrudRepository<Sport, String> {

    Sport findBySportName(String sportName);

    List<Sport> findAllByOrderBySportName();
}