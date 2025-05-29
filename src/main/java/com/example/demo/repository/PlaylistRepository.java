package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, String>{

	Optional<Playlist> findByPlaylistId(String playlistId);
}
