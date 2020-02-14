package fr.rohe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.rohe.model.dto.SearchResult;
import fr.rohe.model.entity.Profile;
import fr.rohe.model.repository.ProfileDao;

@Service
public class SearchService {
	
	@Value("${results.pagesize}")
	private int pageSize;
	
	@Autowired
	private ProfileDao profileDao;

	public Page<SearchResult> search(String text, int pageNumber) {
		PageRequest request = PageRequest.of(pageNumber-1, pageSize);
		
		Page<Profile> results = null;
		
		if(text.trim().length() == 0) {
			results = profileDao.findAll(request);
		}
		else {
			 results = profileDao.findByInterestsNameContainingIgnoreCase(text, request);
		}
		
		return results.map(p -> new SearchResult(p));
	}

}
