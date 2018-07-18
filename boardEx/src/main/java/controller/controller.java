package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.BoardBean;
import service.BoardServiceImple;


@Controller
public class controller {
	
	@Autowired
	private BoardServiceImple boardService;
	
	//게시판 글쓰기 폼
	@RequestMapping("/board_write.nhn")
	public String board_write() {
		return "board_write";
	}
	
	//게시판 저장
	@RequestMapping(value="/board_write_ok.nhn",method=RequestMethod.POST)
	public String board_write_ok(@ModelAttribute BoardBean board) throws Exception{
		System.out.println("board_write_ok");
		boardService.insert(board);
		
		return "redirect:/board_list.nhn";
	}
	
	//게시판 목록
	@RequestMapping("/board_list.nhn")
	public String list(HttpServletRequest request,
			HttpServletResponse response,
			Model model) throws Exception{
		
		Map<String, Object> boardlist = boardService.board_list(request, response);
		
		model.addAllAttributes(boardlist);
		return "/board_list";
	}
	
	//게시판 내용 보기, 삭제폼, 수정폼, 답변글 폼
	@RequestMapping(value = "/board_cont.nhn")
	public ModelAndView board_cont(@RequestParam("board_num") int board_num,
			@RequestParam("page") String page,
			@RequestParam("state") String state, HttpServletResponse response) throws Exception{
		
		if(state.equals("cont")) {//내용보기일때만
			boardService.hit(board_num);//조회수 증가
		}
		
		BoardBean board=boardService.board_cont(board_num);
		
		ModelAndView contM = new ModelAndView();
		contM.addObject("bcont",board);
		contM.addObject("page", page);
		
		if(state.equals("cont")) {
			contM.setViewName("/board_cont");
		}else if(state.equals("edit")) {
			contM.setViewName("/board_edit");
		}else if(state.equals("reply")) {
			contM.setViewName("/board_reply");
		}else if(state.equals("del")) {
			contM.setViewName("/board_del");
		}
		return contM;
	}
	
	//게시판 수정
	@RequestMapping(value="/board_edit_ok.nhn", method = RequestMethod.POST)
	public String board_edit_ok(@ModelAttribute BoardBean b,
			@RequestParam("page") String page, HttpServletResponse response) throws Exception{
		System.out.println("**********board_edit_ok**************");
		
		int result = boardService.edit(response,b);
		
		if(result == 0) {
			return null;
		}else {
			return "redirect:/board_cont.nhn?board_num="+b.getBoard_num()
					+"&page="+page+"&state=cont";
		}
	}
	
	//게시글 삭제
	@RequestMapping(value="/board_del_ok.nhn", method=RequestMethod.POST)
	public String board_del_ok(@RequestParam("board_num") int board_num,
							@RequestParam("page") String page,
							@RequestParam("pwd") String pwd,
							HttpServletResponse response) throws Exception{
		System.out.println("controller - board_del_ok");
		int result=boardService.del_ok(response, board_num, pwd);
		if(result==0) {
			return null;
		}else {
			return "redirect:/board_list.nhn?page="+page;
		}
	}



}
