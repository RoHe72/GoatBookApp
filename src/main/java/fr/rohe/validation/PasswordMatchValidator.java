package fr.rohe.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.rohe.model.entity.SiteUser;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, SiteUser> {

	@Override
	public boolean isValid(SiteUser user, ConstraintValidatorContext c) {
		
		String plainPassword=user.getPlainPassword();
		String repeatPassword=user.getRepeatPassword();
		
		if(plainPassword == null || plainPassword.length()==0) {
			return true;
		}
		
		if(plainPassword==null|| !plainPassword.equals(repeatPassword)) {
			return false;
		}
				
		return true;
	}

}