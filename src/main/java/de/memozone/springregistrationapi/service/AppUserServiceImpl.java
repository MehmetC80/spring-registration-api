package de.memozone.springregistrationapi.service;

import de.memozone.springregistrationapi.entity.AppUser;
import de.memozone.springregistrationapi.model.AppUserModel;
import de.memozone.springregistrationapi.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser registerUser(AppUserModel appUserModel) {

        AppUser appUser = new AppUser();

        appUser.setEmail(appUserModel.getEmail());
        appUser.setFirstName(appUserModel.getFirstName());
        appUser.setLastName(appUserModel.getLastName());
        appUser.setRole("USER");
        appUser.setPassword(passwordEncoder.encode(appUserModel.getMatchingPassword()));

        appUserRepository.save(appUser);

        return appUser;
    }
}
