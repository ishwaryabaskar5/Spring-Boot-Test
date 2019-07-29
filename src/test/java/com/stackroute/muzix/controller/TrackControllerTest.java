package com.stackroute.muzix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.model.Track;
import com.stackroute.muzix.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	private Track track;
	
	@MockBean
	private TrackService trackService;
	
	@InjectMocks
	private TrackController trackController;
	
	private List<Track> list =null;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
		track = new Track(101,"aaa","good");
		list = new ArrayList();
		list.add(track);
	}
	
	@After
	public void tearDown()  {
		track = null;
		list = null;
	}
	
//	Testcase for getAllTrack()
	@Test
	public void getAllTrack() throws Exception{
		when(trackService.getAllTracks()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(track)))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	
//	Testcase for saveTrack()
	@Test
	public void saveTrack() throws Exception {
		when(trackService.saveTrack(track)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(track)))
				.andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}
	
//	Testcase for saveTrackFailure()
	@Test
	public void saveTrackFailure() throws Exception {
		when(trackService.saveTrack(track)).thenThrow(TrackAlreadyExistsException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(track)))
				.andExpect(MockMvcResultMatchers.status().isConflict())
				.andDo(MockMvcResultHandlers.print());
	}
	
//	Testcase for updateTrack()
	@Test
	public void updateTrack() throws Exception {
		when(trackService.updateTrack(anyInt(),any())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/101")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(track)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	
//	Testcase for getAllTrack()
	@Test
	public void deleteNote() throws Exception {
		when(trackService.deleteTrack(anyInt())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/101")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(track)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	
//	method to covert json into string
	private static String jsonToString(final Object obj)
	{
		try{
			return new ObjectMapper().writeValueAsString(obj);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
