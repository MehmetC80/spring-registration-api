package de.memozone.springregistrationapi.event;

import de.memozone.springregistrationapi.entity.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private final AppUser appUser;
    private final String applicationUrl;

    public RegistrationCompleteEvent(AppUser appUser, String applicationUrl) {
        super(appUser);
        this.appUser = appUser;
        this.applicationUrl = applicationUrl;
    }
}
