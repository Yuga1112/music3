package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

	int no;
	String title;
	String content;
	String writer;
	// 음악 수정
	// MusicDTO music;
	int musicNo;
	
	String type;
	String albumImageUrl;
	LocalDateTime regDate;
}
