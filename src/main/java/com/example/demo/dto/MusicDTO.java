package com.example.demo.dto;

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
public class MusicDTO {

	int id;
	
	String name;

	String artist;

	String durationMs;

	String spotifyUrl;

	String albumImageUrl;

}
