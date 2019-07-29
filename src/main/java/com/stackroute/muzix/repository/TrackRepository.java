package com.stackroute.muzix.repository;

import com.stackroute.muzix.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* creates TrackRepository which extends JpaRepository for Track Object */
@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {

}
