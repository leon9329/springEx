package dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.BoardBean;

public interface BoardDao {

	public int insertBoard(BoardBean b) throws Exception;
	public List<BoardBean> getBoardList(int page) throws Exception;
	public int getListCount() throws Exception;
	public void boardHit(int board_num) throws Exception;
	public BoardBean getBoardCont(int board_num) throws Exception;
	public void edit(HttpServletResponse response, BoardBean b) throws Exception;
	public void boardDelete(HttpServletResponse response, int board_num, String board_pass) throws Exception;
	public void refEdit(BoardBean b) throws Exception;
	public void boardReplyOk(BoardBean b) throws Exception;
}
