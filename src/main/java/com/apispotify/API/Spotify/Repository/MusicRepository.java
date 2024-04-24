package com.apispotify.API.Spotify.Repository;

import com.apispotify.API.Spotify.Model.Musics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Musics, Integer> {
}