package fr.rohe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.rohe.model.entity.StatusUpdate;
import fr.rohe.service.StatusUpdateService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller
public class PageController {

	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@Value("${message.error.forbidden}")
	private String accessDeniedMessage;

	@RequestMapping("/")
	ModelAndView home(ModelAndView modelAndView) {

		StatusUpdate statusUpdate = statusUpdateService.getLatest();

		modelAndView.getModel().put("statusUpdate", statusUpdate);

		modelAndView.setViewName("goatbookapp.homepage");

		return modelAndView;
	}

	@RequestMapping("/403")
	ModelAndView accessDenied(ModelAndView modelAndView) {

		modelAndView.getModel().put("message", accessDeniedMessage);
		modelAndView.setViewName("goatbookapp.message");
		return modelAndView;
	}

	@RequestMapping("/about")
	String about() {
		return "goatbookapp.about";
	}

}