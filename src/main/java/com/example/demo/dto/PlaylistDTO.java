package com.example.demo.dto;



import com.example.demo.entity.Playlist;

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
public class PlaylistDTO {


	String id;
	
	String playlistId;
	
	UserDTO user;
	
	public PlaylistDTO(Playlist entity) {
        this.id = entity.getId();
    }
	
}
