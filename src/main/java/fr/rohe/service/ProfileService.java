package fr.rohe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.rohe.model.entity.Profile;
import fr.rohe.model.entity.SiteUser;
import fr.rohe.model.repository.ProfileDao;

@Service
public class ProfileService {
	
	@Autowired
	ProfileDao profileDao;
	
	public void save(Profile profile) {
		profileDao.save(profile);
	}
	
	public Profile getUserProfile(SiteUser user) {
		return profileDao.findByUser(user);
	}
}