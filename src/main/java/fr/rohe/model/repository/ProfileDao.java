package fr.rohe.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import fr.rohe.model.entity.Profile;
import fr.rohe.model.entity.SiteUser;

public interface ProfileDao extends CrudRepository<Profile, Long> {	
	Profile findByUser(SiteUser user);

	List<Profile> findByInterestsNameContainingIgnoreCase(String text);
	Page<Profile> findByInterestsNameContainingIgnoreCase(String text, Pageable request);
	Page<Profile> findAll(Pageable request);
}
