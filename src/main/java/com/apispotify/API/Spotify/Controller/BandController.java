package com.apispotify.API.Spotify.Controller;


import com.apispotify.API.Spotify.Model.Bands;
import com.apispotify.API.Spotify.Model.Users;
import com.apispotify.API.Spotify.Repository.BandRepository;
import com.apispotify.API.Spotify.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/band")
public class BandController {

    private String nonNullOrDefault(String value, String defaultValue) {
        return value != null && !value.isEmpty() ? value : defaultValue;
    }
    @Autowired
    private BandRepository bandRepository;
    @GetMapping("/getAll")
    public List<Bands> getAll() {
        return bandRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Bands> getById(@PathVariable("id") Integer id) {
        return this.bandRepository.findById(id).map(band -> {
            return new ResponseEntity<>(band, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Bands> addUser(@Valid @RequestBody Bands band) {
        Bands savedBand = bandRepository.save(band);
        return new ResponseEntity<>(savedBand, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Bands> updateBand(@PathVariable Integer id, @RequestBody Bands bandsDetails) {
        return bandRepository.findById(id).map(bands -> {
            bands.setName(nonNullOrDefault(bandsDetails.getName(), bands.getName()));
            bands.setDescription(nonNullOrDefault(bandsDetails.getDescription(), bands.getDescription()));
            bands.setImage(nonNullOrDefault(bandsDetails.getImage(), bands.getImage()));
            bands.setMusics(nonNullOrDefault(bandsDetails.getMusics(), bands.getMusics()));


            Bands updatedBand = bandRepository.save(bands);
            return ResponseEntity.ok(updatedBand);
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBand(@PathVariable Integer id) {
        return bandRepository.findById(id).map(band -> {
            bandRepository.delete(band);
            return new ResponseEntity<>("Band deleted successfully", HttpStatus.OK);
        }).orElse(new ResponseEntity<>("Band not found", HttpStatus.NOT_FOUND));
    }

}