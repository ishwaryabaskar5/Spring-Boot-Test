package com.stackroute.muzix.controller;

import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.model.Track;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")       //  class level request mapping
public class TrackController {

	private TrackService trackService;
	
	@Autowired              // constructor based autowiring
	public TrackController(TrackService trackService) {
		this.trackService = trackService;
	}
	
//  maps the http get method url with corresponding service method
	@GetMapping(value = "/tracks")
	public ResponseEntity<?> getAllNotes(){
		ResponseEntity responseEntity;
		List<Track> tracks;
		try{
//			calls getAllTracks() from service
			tracks = trackService.getAllTracks();
			responseEntity = new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);
		} catch (Exception e){
			responseEntity = new ResponseEntity<String>("Exception", HttpStatus.BAD_REQUEST);
		}
//		returns response entity
		return responseEntity;
	}
	
//	maps the http post method url with corresponding service method
	@PostMapping(value = "/track")
	public ResponseEntity<?> saveNote(@RequestBody Track track){
		ResponseEntity responseEntity;
		try{
//			calls saveTrack() from service
			trackService.saveTrack(track);
			responseEntity = new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
		} catch (TrackAlreadyExistsException e){
			responseEntity = new ResponseEntity<String>("Exception", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

//	maps the http put method url with corresponding service method
	@PutMapping(value = "/track/{id}")
	public ResponseEntity<?> updateNote(@PathVariable int id,@RequestBody Track track){
		ResponseEntity responseEntity;
		try{
//			calls updateTrack() from service
			trackService.updateTrack(id,track);
			responseEntity = new ResponseEntity<String>("Successfully Updated", HttpStatus.OK);
		} catch (Exception e){
			responseEntity = new ResponseEntity<String>("Exception", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
//	maps the http delete method url with corresponding service method
	@DeleteMapping(value = "/track/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable("id") int id){
		ResponseEntity responseEntity;
		try{
//			calls deleteTrack() from service
			trackService.deleteTrack(id);
			responseEntity = new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
		} catch (Exception e){
			responseEntity = new ResponseEntity<String>("Exception", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
}
