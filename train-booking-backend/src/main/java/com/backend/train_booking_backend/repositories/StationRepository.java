package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.train_booking_backend.models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
}
