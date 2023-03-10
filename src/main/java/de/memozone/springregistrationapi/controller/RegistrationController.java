package de.memozone.springregistrationapi.controller;

import de.memozone.springregistrationapi.entity.AppUser;
import de.memozone.springregistrationapi.event.RegistrationCompleteEvent;
import de.memozone.springregistrationapi.model.AppUserModel;
import de.memozone.springregistrationapi.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ApplicationEventPublisher publisher;


    @PostMapping("/register")
    public String registerUser(@RequestBody AppUserModel appUserModel, final HttpServletRequest request) {

        AppUser appUser = appUserService.registerUser(appUserModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                appUser,
                applicationUrl(request)
        ));

        return "Registration Successful!";
    }

    @GetMapping("register/verifyRegistration")
public String verifyRegistration(@RequestParam("token") String token){
        String result = appUserService.valideVerificationToken(token);

        if(result.equalsIgnoreCase("valid")){
            return "User Verifies Successfully!";
        }else{
            return "Bad User!!!";
        }
    }



    private String applicationUrl(HttpServletRequest request){

        return "http://"
                + request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getServletPath();
    }

}
