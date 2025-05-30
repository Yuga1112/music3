package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dto.MusicDTO;
import com.example.demo.service.MusicService;

@RestController
@RequestMapping("/")
public class MusicController {

	@Autowired
	MusicService musicService;


	@GetMapping("/")
	public ResponseEntity<List<MusicDTO>> getPlaylistTracks(@RequestParam String playlistId) {
		List<MusicDTO> musicList = musicService.getPlaylist("");
		return ResponseEntity.ok(musicList);
	}

}
