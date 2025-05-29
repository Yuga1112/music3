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
import org.springframework.ui.Model;

import com.example.demo.dto.MusicDTO;
import com.example.demo.service.MusicService;
import com.example.demo.service.PlaylistService;

@RestController
@RequestMapping("/music")
public class MusicController {

	@Autowired
	MusicService musicService;
	
	@Autowired
	PlaylistService playlistService;

	@GetMapping("/playlist")
    public ResponseEntity<List<MusicDTO>> getPlaylistTracks(@RequestParam String playlistId) {
        List<MusicDTO> musicList = musicService.getPlaylist(playlistId);
        return ResponseEntity.ok(musicList);
    }


	// 3. 특정 음악 상세 조회
	@GetMapping("/{id}")
	@GetMapping("/playlist/{playlistId}")
	public ResponseEntity<List<MusicDTO>> getPlaylistTracks(@PathVariable String playlistId) {
	    List<MusicDTO> musicList = musicService.getPlaylist(playlistId);
	    return ResponseEntity.ok(musicList);
	}


	// 4. 음악 검색 (예: Spotify API 연동)
	@GetMapping("/search")
	public ResponseEntity<List<MusicDTO>> searchMusic(@RequestParam String query) throws Exception {
		List<MusicDTO> result = musicService.search(query);
		return ResponseEntity.ok(result);
	}
}
