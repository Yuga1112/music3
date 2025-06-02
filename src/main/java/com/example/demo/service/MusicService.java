package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.MusicDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Music;

public interface MusicService {
	
	//토큰 발급
	String getAccessToken() throws Exception;
	
	//재생목록 가져오기
	List<MusicDTO> getPlaylist(String playlistId);

	//재생목록에서 첫번째 반환
//	MusicDTO getTrackFromPlaylist(String playlistId, int index) throws Exception;
	
	//상세조회
	MusicDTO read(int no);
	
//	// 음악검색
//	String search(String query) throws Exception;
//	
	default Music dtoToEntity(MusicDTO dto) {
		Music entity = Music.builder()
				.id(dto.getId())
				.name(dto.getName())
				.artist(dto.getArtist())
				.durationMs(dto.getDurationMs())
				.spotifyUrl(dto.getSpotifyUrl())
				.albumImageUrl(dto.getAlbumImageUrl())
				.build();
		return entity;
				
	}
	
	default MusicDTO entityToDto(Music entity) {
		MusicDTO dto = MusicDTO.builder()
			.id(entity.getId())
			.name(entity.getName())
			.artist(entity.getArtist())
			.durationMs(entity.getDurationMs())
			.spotifyUrl(entity.getSpotifyUrl())
			.albumImageUrl(entity.getAlbumImageUrl())
			.build();
	return dto;
			
}
	
	
	}




