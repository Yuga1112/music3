package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.dto.MusicDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.service.DiaryService;
import com.example.demo.service.MusicService;
import com.example.demo.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService service;
	
	@Autowired
	DiaryService diaryservice;
	
	@Autowired
	MusicService musicService;
	
	@GetMapping("/home")
	public void main() {
		
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/post")
	public void register() {
		
	}

	// 
	@PostMapping("/post")
	public String registerPost(PostDTO dto, RedirectAttributes redirectAttributes) {
		int no = service.register(dto);
	
		return "redirect:/post/list";
		
	}
	
	@GetMapping("/diary")
	public void register2() {
		
	}
	
	@PostMapping("/diary")
	public String registerDiary(DiaryDTO dto, RedirectAttributes redirectAttributes) {
		int no = diaryservice.register(dto);
		return "redirect:/post/diarylist";
		
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		List<PostDTO> list = service.getList();
		model.addAttribute("list", list);
	}
	
	@GetMapping("/diarylist")
	public void diarylist(Model model) {
		List<DiaryDTO> list = diaryservice.getList();
		model.addAttribute("diarylist", list);
	}
	
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, Model model) {
		PostDTO dto = service.read(no);
		// musicNo 꺼내기
		int musicNo = dto.getMusicNo();
		MusicDTO mdto = musicService.read(musicNo);
		
		model.addAttribute("dto", dto);
		model.addAttribute("mdto", mdto);
	}
	
	@GetMapping("/diaryread")
	public void diaryread(@RequestParam(name = "no") int no, Model model) {
		DiaryDTO dto = diaryservice.read(no);
		
		int musicNo = dto.getMusicNo();
		MusicDTO mdto = musicService.read(no);
		
		model.addAttribute("dto", dto);
		model.addAttribute("mdto", mdto);
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam(name="no") int no, Model model) {
		PostDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/modify")
	public String modifyPost(PostDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/post/list";
	}
	
	@GetMapping("/modify2")
	public void dimodify(@RequestParam(name="no") int no, Model model) {
		DiaryDTO dto = diaryservice.read(no);
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/modify2")
	public String dimodifyPost(DiaryDTO dto, RedirectAttributes redirectAttributes) {
		diaryservice.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/post/diarylist";
	}
	
	@PostMapping("/remove")
	public String removePost(@RequestParam("no") int no) {
		service.remove(no);
		return "redirect:/post/list";
	}

	@PostMapping("/diremove")
	public String diremovePost(@RequestParam("no") int no) {
		diaryservice.remove(no);
		return "redirect:/post/diarylist";
	}
}



