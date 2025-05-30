package com.example.demo.service;

import java.util.List;
import reactor.core.publisher.Mono;

import com.example.demo.dto.MusicDTO;
import com.example.demo.dto.PlaylistDTO;
import com.example.demo.entity.Playlist;


public interface MusicService {
	
	//토큰 발급
	String getAccessToken() throws Exception;
	
	//재생목록 가져오기
	List<MusicDTO> getPlaylist(String playlistId);

	//재생목록에서 첫번째 반환
	MusicDTO getTrackFromPlaylist(String playlistId, int index) throws Exception;
	
	// 음악검색
	String search(String query) throws Exception;
	
	default Playlist dtoToEntity(PlaylistDTO dto) {
		Playlist entity = Playlist.builder()
				.id(dto.getId())
				.playlistId(dto.getPlaylistId())
				.build();
		return entity;
				
	}
	
	default PlaylistDTO entityToDto(Playlist entity) {

		PlaylistDTO dto = PlaylistDTO.builder()
				.id(entity.getId())
				.playlistId(entity.getPlaylistId())
				.build();

		return dto;
	
}
	
	
	}




