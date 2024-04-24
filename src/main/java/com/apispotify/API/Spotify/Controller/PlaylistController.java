package com.apispotify.API.Spotify.Controller;


import com.apispotify.API.Spotify.Model.Plans;
import com.apispotify.API.Spotify.Model.Playlists;
import com.apispotify.API.Spotify.Repository.MusicRepository;
import com.apispotify.API.Spotify.Repository.PlanRepository;
import com.apispotify.API.Spotify.Repository.PlaylistRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private String nonNullOrDefault(String value, String defaultValue) {
        return value != null && !value.isEmpty() ? value : defaultValue;
    }

    private Integer nonNullOrDefault(Integer value, Integer defaultValue) {
        return value != null ? value : defaultValue;
    }
    private Double nonNullOrDefault(Double value, Double defaultValue) {
        return value != null ? value : defaultValue;
    }


    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping("/getAll")
    public List<Playlists> getAll() {
        return playlistRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Playlists> getById(@PathVariable("id") Integer id) {
        return this.playlistRepository.findById(id).map(playlist -> {
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Playlists> addPlan(@Valid @RequestBody Playlists playlist) {
        Playlists savedPlaylist = playlistRepository.save(playlist);
        return new ResponseEntity<>(savedPlaylist, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Playlists> updateBand(@PathVariable Integer id, @RequestBody Playlists playlistDetails) {
        return playlistRepository.findById(id).map(playlist -> {
            playlist.setName(nonNullOrDefault(playlistDetails.getName(), playlistDetails.getName()));
            playlist.setUserId(nonNullOrDefault(playlistDetails.getUserId(), playlist.getUserId()));

            Playlists updatedPlaylist = playlistRepository.save(playlist);
            return ResponseEntity.ok(updatedPlaylist);
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBand(@PathVariable Integer id) {
        return playlistRepository.findById(id).map(playlist -> {
            playlistRepository.delete(playlist);
            return new ResponseEntity<>("Playlist deleted successfully", HttpStatus.OK);
        }).orElse(new ResponseEntity<>("Playlist not found", HttpStatus.NOT_FOUND));
    }

}