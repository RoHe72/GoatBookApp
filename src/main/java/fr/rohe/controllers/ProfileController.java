package fr.rohe.controllers;
import java.util.Optional;

import javax.validation.Valid;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.rohe.model.entity.Interest;
import fr.rohe.model.entity.Profile;
import fr.rohe.model.entity.SiteUser;
import fr.rohe.service.InterestService;
import fr.rohe.service.ProfileService;
import fr.rohe.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private InterestService interestService;

	@Autowired
	private PolicyFactory htmlPolicy;

	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		return userService.get(email);
	}
	
	private ModelAndView showProfile(SiteUser user) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(user == null) {
			modelAndView.setViewName("redirect:/");
			return modelAndView;
		}
		
		Profile profile = profileService.getUserProfile(user);

		if (profile == null) {
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);
		}

		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);

		
		modelAndView.getModel().put("userId", user.getId());
		modelAndView.getModel().put("profile", webProfile);

		modelAndView.setViewName("goatbookapp.profile");
		
		return modelAndView;
	}

	@RequestMapping(value = "/profile")
	public ModelAndView showProfile() {
		SiteUser user = getUser();
		
		String firstname = user.getFirstname();
		String surname = user.getSurname();
		
		ModelAndView modelAndView = showProfile(user);
		
		modelAndView.getModel().put("ownProfile", true);
		modelAndView.getModel().put("firstname", firstname);
		modelAndView.getModel().put("surname", surname);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/profile/{id}")
	public ModelAndView showProfile(@PathVariable("id") Long id) {

		Optional<SiteUser> userOptional = userService.get(id);
		SiteUser user = userOptional.get();
				
		String firstname = user.getFirstname();
		String surname = user.getSurname();

		ModelAndView modelAndView = showProfile(user);
		
		modelAndView.getModel().put("ownProfile", false);
		modelAndView.getModel().put("firstname", firstname);
		modelAndView.getModel().put("surname", surname);
		
		return modelAndView;
	}

	@RequestMapping(value = "/edit-profile-about", method = RequestMethod.GET)
	public ModelAndView editProfileAbout(ModelAndView modelAndView) {

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);

		modelAndView.getModel().put("profile", webProfile);
		modelAndView.setViewName("goatbookapp.editProfileAbout");

		return modelAndView;
	}

	@RequestMapping(value = "/edit-profile-about", method = RequestMethod.POST)
	public ModelAndView editProfileAbout(ModelAndView modelAndView, @Valid Profile webProfile, BindingResult result) {

		modelAndView.setViewName("goatbookapp.editProfileAbout");

		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);

		profile.safeMergeFrom(webProfile, htmlPolicy);

		if (!result.hasErrors()) {
			profileService.save(profile);
			modelAndView.setViewName("redirect:/profile");
		}

		return modelAndView;
	}

	
	@RequestMapping(value="/save-interest", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveInterest(@RequestParam("name") String interestName) {
		
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);
		
		String cleanedInterestName = htmlPolicy.sanitize(interestName);
		
		Interest interest = interestService.createIfNotExists(cleanedInterestName);
		
		profile.addInterest(interest);
		profileService.save(profile);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete-interest", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteInterest(@RequestParam("name") String interestName) {
		
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);
	
		profile.removeInterest(interestName);
	
		profileService.save(profile);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	

}