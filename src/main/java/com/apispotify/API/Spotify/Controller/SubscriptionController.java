package com.apispotify.API.Spotify.Controller;


import com.apispotify.API.Spotify.Model.Plans;
import com.apispotify.API.Spotify.Model.Subscriptions;
import com.apispotify.API.Spotify.Repository.MusicRepository;
import com.apispotify.API.Spotify.Repository.PlanRepository;
import com.apispotify.API.Spotify.Repository.SubscriptionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

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
    private SubscriptionRepository subscriptionRepository;

    @GetMapping("/getAll")
    public List<Subscriptions> getAll() {
        return subscriptionRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Subscriptions> getById(@PathVariable("id") Integer id) {
        return this.subscriptionRepository.findById(id).map(subscription -> {
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Subscriptions> addPlan(@Valid @RequestBody Subscriptions subscription) {
        Subscriptions savedSubscription = subscriptionRepository.save(subscription);
        return new ResponseEntity<>(savedSubscription, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Subscriptions> updateBand(@PathVariable Integer id, @RequestBody Subscriptions subscriptionsDetails) {
        return subscriptionRepository.findById(id).map(subscription -> {
            subscription.setUserId(nonNullOrDefault(subscription.getUserId(), subscriptionsDetails.getUserId()));
            subscription.setPlanId(nonNullOrDefault(subscription.getPlanId(), subscriptionsDetails.getPlanId()));
            subscription.setStatus(nonNullOrDefault(subscription.getStatus(), subscriptionsDetails.getStatus()));


            Subscriptions updatedSubscription = subscriptionRepository.save(subscription);
            return ResponseEntity.ok(updatedSubscription);
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBand(@PathVariable Integer id) {
        return subscriptionRepository.findById(id).map(subscription -> {
            subscriptionRepository.delete(subscription);
            return new ResponseEntity<>("Subscription deleted successfully", HttpStatus.OK);
        }).orElse(new ResponseEntity<>("Subscription not found", HttpStatus.NOT_FOUND));
    }

}