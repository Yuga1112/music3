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
		
		// post dto -> entity
		
		// music 꺼내서 entity로 만드는 중
		
		// 게시물 등록할 때
		// 선택한 음악을 entity 만드는 거
	    
	    int musicNo = dto.getMusicNo();
	    
	    // Music를 만드는 이유
	    // Post의 Music 필드에 넣기 위해서
	    // Music는 외래키!
	    // 외래키를 만들때는 모든 정보를 저장할 필요x
	    // pk만 넣으면 됨
	    Music music = Music.builder()
	    		.id(musicNo)
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

//	    MusicDTO musicDTO = MusicDTO.builder()
//	            .id(music.getId())
//	            .name(music.getName())
//	            .artist(music.getArtist())
//	            .durationMs(music.getDurationMs())
//	            .spotifyUrl(music.getSpotifyUrl())
//	            .albumImageUrl(music.getAlbumImageUrl())
//	            .build(); 

	    return PostDTO.builder()
	            .no(entity.getNo())
	            .title(entity.getTitle())
	            .content(entity.getContent())
	            .writer(entity.getWriter())
	            .albumImageUrl(entity.getAlbumImageUrl())
	            .musicNo(entity.getMusic().getId())
	            .build();
	}


}
