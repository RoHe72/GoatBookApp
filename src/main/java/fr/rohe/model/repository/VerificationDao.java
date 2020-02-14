package fr.rohe.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.rohe.model.entity.VerificationToken;

@Repository
public interface VerificationDao extends CrudRepository<VerificationToken, Long> {
	VerificationToken findByToken(String token);
}
