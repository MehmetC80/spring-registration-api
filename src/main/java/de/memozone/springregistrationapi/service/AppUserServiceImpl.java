package de.memozone.springregistrationapi.service;

import de.memozone.springregistrationapi.entity.AppUser;
import de.memozone.springregistrationapi.entity.VerificationToken;
import de.memozone.springregistrationapi.model.AppUserModel;
import de.memozone.springregistrationapi.repository.AppUserRepository;
import de.memozone.springregistrationapi.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired(required = true)
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser registerUser(AppUserModel appUserModel) {

        AppUser appUser = new AppUser();

        appUser.setEmail(appUserModel.getEmail());
        appUser.setFirstName(appUserModel.getFirstName());
        appUser.setLastName(appUserModel.getLastName());
        appUser.setRole("USER");
        appUser.setPassword(passwordEncoder.encode(appUserModel.getPassword()));

        appUserRepository.save(appUser);

        return appUser;
    }

    @Override
    public void saveVerificationTokenForUser(String token,AppUser appUser) {

        VerificationToken verificationToken = new VerificationToken(token,appUser);

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String valideVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken == null){
            return "invalid token";
        }

        AppUser appUser = verificationToken.getAppUser();
        Calendar calendar = Calendar.getInstance();

        if(verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0){
            verificationTokenRepository.delete(verificationToken);
            return "expired token";
        }
        appUser.setEnabled(true);
        appUserRepository.save(appUser);
        return "valid";
    }
}
