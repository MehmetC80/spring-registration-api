package de.memozone.springregistrationapi.event.listener;

import de.memozone.springregistrationapi.entity.AppUser;
import de.memozone.springregistrationapi.event.RegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        //create the verification token for the AppUser with link

        AppUser appUser = event.getAppUser();
        String token = UUID.randomUUID().toString();

        //Send email to appUser
    }
}
