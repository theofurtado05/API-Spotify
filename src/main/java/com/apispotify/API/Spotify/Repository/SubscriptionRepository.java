package com.apispotify.API.Spotify.Repository;

import com.apispotify.API.Spotify.Model.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Integer> {
}
