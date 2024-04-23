package com.apispotify.API.Spotify.Repository;

import com.apispotify.API.Spotify.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}