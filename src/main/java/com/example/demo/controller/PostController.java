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

import com.example.demo.dto.MusicDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.service.MusicService;
import com.example.demo.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService service;
	
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
	
	@GetMapping("/list")
	public void list(Model model) {
		List<PostDTO> list = service.getList();
		model.addAttribute("list", list);
	}
	
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, Model model) {
		PostDTO dto = service.read(no);
		MusicDTO mdto = service.read(no);
		// 이거 서비스구현 넣어야 함
		
		model.addAttribute("dto", dto);
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
		return  "redirect:/post/read";
	}
	@PostMapping("/remove")
	public String removePost(@RequestParam("no") int no) {
		service.remove(no);
		return "redirect:/post/list";
	}
	
	@GetMapping("/callback")
	public String callback(@RequestParam String code) {

	    return "redirect:/post";
	}
}
