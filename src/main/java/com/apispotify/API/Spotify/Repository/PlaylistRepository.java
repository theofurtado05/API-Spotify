package com.apispotify.API.Spotify.Repository;

import com.apispotify.API.Spotify.Model.Playlists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlists, Integer> {
}
