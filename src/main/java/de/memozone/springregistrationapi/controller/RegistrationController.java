package de.memozone.springregistrationapi.controller;

import de.memozone.springregistrationapi.entity.AppUser;
import de.memozone.springregistrationapi.event.RegistrationCompleteEvent;
import de.memozone.springregistrationapi.model.AppUserModel;
import de.memozone.springregistrationapi.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody AppUserModel appUserModel) {

        AppUser appUser = appUserService.registerUser(appUserModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                appUser,
                "url"
        ));

        return "Registration Successful!";
    }


}
