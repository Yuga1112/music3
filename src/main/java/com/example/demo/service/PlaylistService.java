package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PlaylistDTO;

public interface PlaylistService {
	
	List<PlaylistDTO> getAllPlaylists();
	
    PlaylistDTO getPlaylistById(String id);

}


