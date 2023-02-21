package de.memozone.springregistrationapi.service;

import de.memozone.springregistrationapi.entity.AppUser;
import de.memozone.springregistrationapi.model.AppUserModel;

public interface AppUserService {
    AppUser registerUser(AppUserModel appUserModel);

    void saveVerificationTokenForUser(String token, AppUser appUser);
}
