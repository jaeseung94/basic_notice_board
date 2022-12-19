package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor // 모든 인스턴스 변수를 파라미터로 하는 생성자 생성 ex) new BoardController(BoardService service...)
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("------------------------------------------------------");
		log.info("list  : " + cri);
		log.info("------------------------------------------------------");
		model.addAttribute("list", service.getList(cri));
		
		int total = service.getTotal(cri);
		
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri,total));
	}
	
//	@GetMapping("/list.jsp")
//	public void list2(Model model) {
//		log.info("------------------------------------------------------");
//		log.info("list.jsp 실행");
//		log.info("------------------------------------------------------");
//		model.addAttribute("list", service.getList());
//	}
	
	@GetMapping("/register")
	public void register(Model model) {
		log.info("------------------------------------------------------");
		log.info("register GET 실행");
		log.info("------------------------------------------------------");
	}  
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri,  RedirectAttributes rttr) {
		log.info("modify : " + board);
		if(service.modify(board)) {
			log.info("-----여기실행----");
			rttr.addFlashAttribute("result", "success");
		}
		
		// url 매개변수 지정
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		
		log.info("------------------------------------------------------");
		log.info("modify 실행");
		log.info("------------------------------------------------------");
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register : " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		log.info("------------------------------------------------------");
		log.info("register 실행");
		log.info("------------------------------------------------------");
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
		log.info(service.get(bno));
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove : " + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		log.info("------------------------------------------------------");
		log.info("remove 실행");
		log.info("------------------------------------------------------");
		return "redirect:/board/list";
	}

}
