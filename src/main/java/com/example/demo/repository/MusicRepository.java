package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Music;


public interface MusicRepository extends JpaRepository<Music, Integer> { 
}

