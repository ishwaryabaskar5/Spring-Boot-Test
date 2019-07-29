package com.stackroute.muzix.repository;

import com.stackroute.muzix.model.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {
	
	@Autowired
	TrackRepository trackRepository;
	Track track;
	
	@Before
	public void setUp() {
		track = new Track(101,"bbb","bbb");
	}
	
	@After
	public void tearDown() {
		trackRepository.deleteAll();
	}
	
	
//	method to check save() method of repository
	@Test
	public void testSaveTrack(){
		trackRepository.save(track);
		Track fetchUser = trackRepository.findById(track.getId()).get();
		Assert.assertEquals(101,fetchUser.getId());
		
	}
	
//	method to check save() method of repository
	@Test
	public void testSaveTrackFailure(){
		Track testUser = new Track(102,"bsbd","shsgd");
		trackRepository.save(track);
		Track fetchUser = trackRepository.findById(track.getId()).get();
		Assert.assertNotSame(testUser,track);
	}
	
//	method to check deleteById() method of repository
	@Test(expected = NoSuchElementException.class)
	public void testDeleteTrack(){
		Track testUser = new Track(102,"bsbd","shsgd");
		trackRepository.save(testUser);
		trackRepository.deleteById(testUser.getId());
		Assert.assertNotEquals(testUser,trackRepository.findById(testUser.getId()).get());
	}
	
//	method to check save() and existsById() method of repository
	@Test
	public void testUpdateTrack(){
		trackRepository.save(track);
		trackRepository.existsById(track.getId());
		track.setComment("good");
		trackRepository.save(track);
		Track fetchUser = trackRepository.findById(track.getId()).get();
		Assert.assertEquals("good",fetchUser.getComment());
		
	}

//	method to check findAll() method of repository
	@Test
	public void testGetAllTrack(){
		Track track1 = new Track(102,"bsbd","shsgd");
		Track track2 = new Track(103,"sjd","ndsj");
		trackRepository.save(track1);
		trackRepository.save(track2);
		
		List<Track> list = trackRepository.findAll();
		Assert.assertEquals(102,list.get(0).getId());
		
	}
}



	
