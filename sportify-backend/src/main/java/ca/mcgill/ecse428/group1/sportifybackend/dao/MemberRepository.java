package ca.mcgill.ecse428.group1.sportifybackend.dao;

import java.util.List;

import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.group1.sportifybackend.model.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	Member findByUsername(String username);

	List<Member> findAllByOrderByUsername();

	List<Member> findByUsernameIgnoreCaseContainingOrderByUsername(String usernameFragment);

	Member findBySportsContaining(SpecificSport ss);
}
