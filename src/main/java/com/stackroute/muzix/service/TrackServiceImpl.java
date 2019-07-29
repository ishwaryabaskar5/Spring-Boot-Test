package com.stackroute.muzix.service;

import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.model.Track;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
	
	TrackRepository trackRepository;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}
	
//	method for getting all the tracks in database and returns as a response entity
	@Override
	public List<Track> getAllTracks() {
		List<Track> trackList = trackRepository.findAll();
		return trackList;
	}
	
// 	method for creating a new track DB
	@Override
	public boolean saveTrack(Track track) throws TrackAlreadyExistsException {
		if(trackRepository.existsById(track.getId()))
			throw new TrackAlreadyExistsException();
			trackRepository.save(track);
			return true;
	}

// 	method for delete a track from DB using id
	@Override
	public boolean deleteTrack(int id) {
			trackRepository.deleteById(id);
			return true;
	}
	
// 	method for update a already available track in DB
	@Override
	public boolean updateTrack(int id, Track track) {
			if(trackRepository.existsById(id) == true){
				trackRepository.save(track);
				return true;
			}
		return false;
	}
}
