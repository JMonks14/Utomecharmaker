package com.tome.main.Repos;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tome.main.Enitities.PasswordResetToken;
import com.tome.main.Enitities.Player;



public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
	
	PasswordResetToken findByToken(String token);

    PasswordResetToken findByPlayer(Player player);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);

}
