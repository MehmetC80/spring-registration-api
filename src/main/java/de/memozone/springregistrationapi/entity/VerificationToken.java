package de.memozone.springregistrationapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class VerificationToken {

    // EXPIRATION_TIME = 10 Minutes
    private static final int EXPIRATION_TIME = 10;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String token;

    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_APP_USER_VERIFY_TOKEN"))
    private AppUser appUser;

    public VerificationToken(String token, AppUser appUser) {
        super();
        this.token = token;
        this.appUser = appUser;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationTime);
        return new Date(calendar.getTime().getTime());
    }


}
