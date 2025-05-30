//package com.example.demo.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.dto.PlaylistDTO;
//import com.example.demo.entity.Playlist;
//import com.example.demo.repository.PlaylistRepository;
//
//@Service
//public class PlaylistServiceImpl implements PlaylistService {
//	
//	@Autowired
//	PlaylistRepository playlistRepository;
//
//	@Override
//	public List<PlaylistDTO> getAllPlaylists() {
//		
//		 List<Playlist> entities = playlistRepository.findAll();
//
//	        List<PlaylistDTO> dtoList = new ArrayList<>();
//	        for (Playlist entity : entities) {
//	            dtoList.add(new PlaylistDTO(entity));  
//	        }
//		return dtoList;
//	}
//
//	@Override
//	public PlaylistDTO getPlaylistById(String id) {
//		
//		 Optional<Playlist> entityO = playlistRepository.findById(id);
//
//		    if (entityO.isPresent()) {
//		        Playlist entity = entityO.get();
//		        return new PlaylistDTO(entity); 
//		    } else {
//		        return null; 
//		    }
//	}
//
//}
