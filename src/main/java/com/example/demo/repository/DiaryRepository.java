package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Diary;


//JpaRepository 상속받기
//엔티티와 pk타입 지정

//QuerydslPredicateExecutor 상속 추가
public interface DiaryRepository extends JpaRepository<Diary, Integer> { 
}
