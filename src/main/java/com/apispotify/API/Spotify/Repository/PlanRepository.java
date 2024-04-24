package com.apispotify.API.Spotify.Repository;

import com.apispotify.API.Spotify.Model.Plans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plans, Integer> {
}
