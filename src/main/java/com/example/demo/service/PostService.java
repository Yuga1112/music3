	package com.example.demo.service;

import java.util.List;



import com.example.demo.dto.MusicDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Music;
import com.example.demo.entity.Post;



public interface PostService {

	//게시물 등록
	int register(PostDTO dto);
	
	//목록조회1
	List<PostDTO> getList();
	
	//목록조회2
	//Page<PostDTO> getList(int pageNumber);
	
	//상세조회
	PostDTO read(int no);
	
	//수정
	void modify(PostDTO dto);
	
	//삭제
	void remove(int no);
	
	default Post dtoToEntity(PostDTO dto) {
	    
	    MusicDTO musicDto = dto.getMusic();

	    Music music = Music.builder()
	            .id(musicDto.getId())
	            .name(musicDto.getName())
	            .artist(musicDto.getArtist())
	            .durationMs(musicDto.getDurationMs())
	            .spotifyUrl(musicDto.getSpotifyUrl())
	            .albumImageUrl(musicDto.getAlbumImageUrl())
	            .build(); 

	    return Post.builder()
	            .no(dto.getNo())
	            .title(dto.getTitle())
	            .content(dto.getContent())
	            .writer(dto.getWriter())
	            .albumImageUrl(dto.getAlbumImageUrl())
	            .music(music)
	            .build();
	}
	
	default PostDTO entityToDto(Post entity) {
	    Music music = entity.getMusic();

	    MusicDTO musicDTO = MusicDTO.builder()
	            .id(music.getId())
	            .name(music.getName())
	            .artist(music.getArtist())
	            .durationMs(music.getDurationMs())
	            .spotifyUrl(music.getSpotifyUrl())
	            .albumImageUrl(music.getAlbumImageUrl())
	            .build(); 

	    return PostDTO.builder()
	            .no(entity.getNo())
	            .title(entity.getTitle())
	            .content(entity.getContent())
	            .writer(entity.getWriter())
	            .albumImageUrl(entity.getAlbumImageUrl())
	            .music(musicDTO)
	            .build();
	}


}
