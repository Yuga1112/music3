package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Diary;
import com.example.demo.entity.Music;
import com.example.demo.entity.Post;
import com.example.demo.repository.DiaryRepository;
import com.example.demo.repository.PostRepository;

@Service
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	DiaryRepository repository;

	//등록
	@Override
	public int register(DiaryDTO dto) {
		
		// 임시로 음악아이디 입력
		// 나중에 화면에서 받아오세요
		
//		dto.setMusicNo(1);
		
	
		// 등록을 한다 엔티티를 dto로 변환해서 
		Diary entity = dtoToEntity(dto);
		repository.save(entity);
		int newNo = entity.getNo();
		
		return newNo;
	}

	@Override
	public List<DiaryDTO> getList() {
		List<Diary> result = repository.findAll();
		List<DiaryDTO> list = new ArrayList<>();
		list = result.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public DiaryDTO read(int no) {
		
		Optional<Diary> result = repository.findById(no);
		
		if(result.isPresent() ) {
			Diary diary = result.get();
			DiaryDTO diaryDTO = entityToDto(diary);
			return diaryDTO;
		} else {
		return null;
	}
	}

	@Override
	public void modify(DiaryDTO dto) {
		
		Optional<Diary> result = repository.findById(dto.getNo());
		if(result.isPresent()) {
			Diary entity = result.get();
			
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			
			repository.save(entity);
		}
		
	}

	@Override
	public void remove(int no) {
		Optional<Diary> result = repository.findById(no);
		if(result.isPresent()) {
			repository.deleteById(no);
		}
		
	}
	
}
