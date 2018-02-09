package my.remind.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.remind.board.dao.ContentDAO;

@Controller
@RequestMapping("/contents/*")
public class BoardController {
	private final int DEFAULTPAGE= 1;
	
	@Inject
	private ContentDAO dao;
	
	@RequestMapping(value = "/lsit", method = RequestMethod.GET)
	public String getList(Model model) {
		String view = "/board/ContentList";
		int pageNumber = DEFAULTPAGE;
		
		model.addAttribute("", dao.getContents(1) );
		
		return view;
	}
}
