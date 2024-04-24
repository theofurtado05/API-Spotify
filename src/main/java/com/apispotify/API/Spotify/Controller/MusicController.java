package com.apispotify.API.Spotify.Controller;


import com.apispotify.API.Spotify.Model.Bands;
import com.apispotify.API.Spotify.Model.Musics;
import com.apispotify.API.Spotify.Model.Users;
import com.apispotify.API.Spotify.Repository.BandRepository;
import com.apispotify.API.Spotify.Repository.MusicRepository;
import com.apispotify.API.Spotify.Repository.UserRepository;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {

    private String nonNullOrDefault(String value, String defaultValue) {
        return value != null && !value.isEmpty() ? value : defaultValue;
    }

    private Integer nonNullOrDefault(Integer value, Integer defaultValue) {
        return value != null ? value : defaultValue;
    }

    @Autowired
    private MusicRepository musicRepository;

    @GetMapping("/getAll")
    public List<Musics> getAll() {
        return musicRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Musics> getById(@PathVariable("id") Integer id) {
        return this.musicRepository.findById(id).map(music -> {
            return new ResponseEntity<>(music, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Musics> addMusic(@Valid @RequestBody Musics music) {
        Musics savedMusic = musicRepository.save(music);
        return new ResponseEntity<>(savedMusic, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Musics> updateBand(@PathVariable Integer id, @RequestBody Musics musicDetails) {
        return musicRepository.findById(id).map(music -> {
            music.setTitle(nonNullOrDefault(musicDetails.getTitle(), music.getTitle()));
            music.setDuration(nonNullOrDefault(musicDetails.getDuration(), music.getDuration()));
            music.setBand(nonNullOrDefault(musicDetails.getBand(), music.getBand()));

            Musics updatedBand = musicRepository.save(music);
            return ResponseEntity.ok(updatedBand);
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBand(@PathVariable Integer id) {
        return musicRepository.findById(id).map(music -> {
            musicRepository.delete(music);
            return new ResponseEntity<>("Music deleted successfully", HttpStatus.OK);
        }).orElse(new ResponseEntity<>("Music not found", HttpStatus.NOT_FOUND));
    }

}