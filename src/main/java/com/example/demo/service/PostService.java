	package com.example.demo.service;

import java.util.List;




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
	    
	    int musicNo = dto.getMusicNo();
	    Music music = Music.builder()
	    		.id(musicNo)
	            .build(); 

	    return Post.builder()
	            .no(dto.getNo())
	            .title(dto.getTitle())
	            .content(dto.getContent())
	            .writer(dto.getWriter())
	            .type(dto.getType())
	            .albumImageUrl(dto.getAlbumImageUrl())
	            .music(music)
	            .build();
	}
	
	default PostDTO entityToDto(Post entity) {
	    Music music = entity.getMusic();

	    return PostDTO.builder()
	            .no(entity.getNo())
	            .title(entity.getTitle())
	            .content(entity.getContent())
	            .writer(entity.getWriter())
	            .albumImageUrl(entity.getAlbumImageUrl())
	            .regDate(entity.getRegDate())
	            .type(entity.getType())
	            .musicNo(entity.getMusic().getId())
	            .build();
	}


}
