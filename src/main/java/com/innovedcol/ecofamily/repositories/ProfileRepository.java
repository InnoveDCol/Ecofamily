package com.innovedcol.ecofamily.repositories;

import com.innovedcol.ecofamily.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
