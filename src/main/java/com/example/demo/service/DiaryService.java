	package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.entity.Diary;
import com.example.demo.entity.Music;




public interface DiaryService {

	//게시물 등록
	int register(DiaryDTO dto);
	
	//목록조회1
	List<DiaryDTO> getList();
	
	//상세조회
	DiaryDTO read(int no);
	
	//수정
	void modify(DiaryDTO dto);
	
	//삭제
	void remove(int no);
	
	default Diary dtoToEntity(DiaryDTO dto) {
	    
	    int musicNo = dto.getMusicNo();
	    Music music = Music.builder()
	    		.id(musicNo)
	            .build(); 

	    return Diary.builder()
	            .no(dto.getNo())
	            .title(dto.getTitle())
	            .content(dto.getContent())
	            .writer(dto.getWriter())
	            .albumImageUrl(dto.getAlbumImageUrl())
	            .music(music)
	            .build();
	}
	
	default DiaryDTO entityToDto(Diary entity) {
	    Music music = entity.getMusic();

	    return DiaryDTO.builder()
	            .no(entity.getNo())
	            .title(entity.getTitle())
	            .content(entity.getContent())
	            .writer(entity.getWriter())
	            .albumImageUrl(entity.getAlbumImageUrl())
	            .regDate(entity.getRegDate())
	            .musicNo(entity.getMusic().getId())
	            .build();
	}


}
