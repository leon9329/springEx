package service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDaoImpl;
import model.BoardBean;



@Service
public class BoardServiceImple implements BoardService{
	
	@Autowired
	private BoardDaoImpl boardDao;

	//게시판 저장
	public int insert(BoardBean b) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardService_insert");
		return boardDao.insertBoard(b);
	}
	
	//게시판 목록
	public Map<String, Object> board_list(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		
		int page=1;
		int limit=10;//한 화면에 출력할 레코드 수
		
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		//총 리스트 수를 받아옴.
		int listcount=boardDao.getListCount();
		
		boardlist=boardDao.getBoardList(page);//리스트 받기
		
		//총 페이지 수
		int maxpage=(int)((double)listcount/limit+0.95);
		
		//현제 페이지에 보여줄 시작 페이지 수(1,11,21 등)
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		//현제 페이지에 보여줄 마지막 페이지 수(10,20,30 등..)
		int endpage=maxpage;
		
		if(endpage > startpage + 10 -1)
			endpage=startpage+10-1;
		
		Map<String,Object> resultMap=new HashMap<String, Object>();
		
		resultMap.put("page", page);
		resultMap.put("startpage", startpage);
		resultMap.put("endpage", endpage);
		resultMap.put("maxpage", maxpage);
		resultMap.put("listcount", listcount);
		resultMap.put("boardlist", boardlist);
		
		return resultMap;
	}
	
	//조회수 증가
	public void hit(int board_num) throws Exception{
		boardDao.boardHit(board_num);
	}

	//상세 정보
	public BoardBean board_cont(int board_num) throws Exception {
		BoardBean board=boardDao.getBoardCont(board_num);
		return board;
	}
	
	//게시글 수정
	public int edit(HttpServletResponse response, BoardBean b) throws Exception{
		PrintWriter out=response.getWriter();
		response.setContentType("Text/html;charset=UTF-8");
		int result=0;
		
		BoardBean board = boardDao.getBoardCont(b.getBoard_num());
		if(!board.getBoard_pass().equals(b.getBoard_pass())) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
			return result;
		}else {
			boardDao.edit(response,b);
			result=1;
		}
		return result;
	}
	
	//게시글 삭제
	public int del_ok(HttpServletResponse response, int board_num, String pwd) throws Exception{
		System.out.println("service - del_ok");
		PrintWriter out = response.getWriter();
		response.setContentType("Text/html;charset=UTF-8");
		int result=0;
		
		BoardBean board = boardDao.getBoardCont(board_num);
		if(!board.getBoard_pass().equals(pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
			return result;
		}else {
			boardDao.boardDelete(response,board_num,pwd);
			result=1;
		}
		return result;
	}
	
	//댓글 달기
	public void reply_ok(BoardBean b) throws Exception{
		System.out.println("reply_ok");
		boardDao.refEdit(b);
		System.out.println("refEdit 나옴");
		b.setBoard_re_lev(b.getBoard_re_lev()+1);
		b.setBoard_re_seq(b.getBoard_re_seq()+1);
		
		boardDao.boardReplyOk(b);
		
	}
	
	

}
