package com.innovedcol.ecofamily.repositories;

import com.innovedcol.ecofamily.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

}
