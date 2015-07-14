package com.shop.logic.validator;

import com.shop.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Igor on 04.07.2015.
 */
@FacesValidator("com.shop.logic.validator.UsernameValidator")
public class UsernameValidator implements Validator {
    private static final String USERNAME_PATTERN = "^[_A-Za-z0-9-]+";

    @Autowired
    private UserService userService;

    private Pattern pattern;
    private Matcher matcher;

    public UsernameValidator(){
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        matcher = pattern.matcher(o.toString());
        if(!matcher.matches()){

            FacesMessage msg =
                    new FacesMessage("Username validation failed.",
                            "Invalid Username format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if (userService.exists(o.toString())) {
            FacesMessage msg =
                    new FacesMessage("Username validation failed.",
                            "This Username is not free.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
