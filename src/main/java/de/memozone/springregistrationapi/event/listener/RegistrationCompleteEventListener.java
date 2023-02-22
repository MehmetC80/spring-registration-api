package de.memozone.springregistrationapi.event.listener;

import de.memozone.springregistrationapi.entity.AppUser;
import de.memozone.springregistrationapi.event.RegistrationCompleteEvent;
import de.memozone.springregistrationapi.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private AppUserService appUserService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        //create the verification token for the AppUser with link
        AppUser appUser = event.getAppUser();
        String token = UUID.randomUUID().toString();
        appUserService.saveVerificationTokenForUser(token, appUser);
        //Send email to appUser
        String url = event.getApplicationUrl() + "/verifyRegistration?token="+ token;

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}",url);

    }
}
