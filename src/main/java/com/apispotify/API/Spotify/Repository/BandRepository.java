package com.apispotify.API.Spotify.Repository;

import com.apispotify.API.Spotify.Model.Bands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Bands, Integer> {
}