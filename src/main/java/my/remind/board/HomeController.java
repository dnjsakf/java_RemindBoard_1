package my.remind.board;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import my.remind.board.dao.ContentDAO;
import my.remind.board.vo.ContentVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	private ContentDAO dao;
	
	private final int DEFAULTPAGE= 1;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model, HttpServletRequest request) {
		String view = "/board/ContentList";
		int pageNumber = DEFAULTPAGE;

		if( request.getParameter("page") != null ) {
			pageNumber = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<ContentVO> contents = dao.getContents(pageNumber);
		int firstContentId = contents.get(0).getcNo();
		int lastContentId = contents.get( contents.size() - 1 ).getcNo();
		
		model.addAttribute("contents", dao.getContents(pageNumber));
		model.addAttribute("pages", Math.round( dao.getContentsCount() / 10 ) + 1);
		model.addAttribute("existNext", dao.checkNextPage(lastContentId) > 0);
		model.addAttribute("existPrev", dao.checkPrevPage(firstContentId) > 0);
		
		return view;
	}
	/**
	 * 1. 게시글 목록 보여주기
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getContent(Model model, HttpServletRequest request) {
		int contentId = -1;
		if( request.getParameter("contentId") != null ) {
			contentId = Integer.parseInt(request.getParameter("contentId"));
			model.addAttribute("mode", "view");
			model.addAttribute("content", dao.getContent(contentId));
			return "/board/ContentView";
		}
		return "/board/ContentList";	// To ErrorPage
	}
	
	/**
	 * 2. 게시글 작성
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeContent(Model model) {
		model.addAttribute("mode", "write");
		return "/board/ContentView";
	}
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String doWriteContent(@ModelAttribute ContentVO content, HttpServletRequest request) {
		int insertedId = dao.insertContent(content);
		if( insertedId > 0 ) {
			return "redirect:/view?page=1&contentId="+insertedId;
		}
		return "/board/ContentView";
	}
	
	/**
	 * 3. 게시글 수정
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateContent(Model model) {
		model.addAttribute("mode", "update");
		return "board/ContentView";
	}
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String doUpdateContent(@ModelAttribute ContentVO content, Model model) {
		// Run
		dao.updateContent(content);
		return "redirect:/view?page=1&contentId="+content.getcNo();
	}
	
	/**
	 * 4. 게시글 삭제
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteContent(Model model) {
		model.addAttribute("mode","update");
		return "/board/ContentView";
	}
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String doDeleteContent(@ModelAttribute ContentVO content, HttpServletRequest request) {
		System.out.println("[id] " + content.getcNo());
		dao.deleteContent(content.getcNo());
		return "redirect:/list";
	}
}
