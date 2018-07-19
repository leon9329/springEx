package dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.BoardBean;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 저장
	public int insertBoard(BoardBean b) throws Exception {
		System.out.println("BoardDao_insert");
		 return sqlSession.insert("Test.board_insert",b);
		
	};
	
	//게시물 목록
	@Override
	public List<BoardBean> getBoardList(int page) throws Exception {
		// TODO Auto-generated method stub
		List<BoardBean> list = sqlSession.selectList("Test.board_list",page);
		return list;
	}
	
	//게시판 총 갯수
	@Override
	public int getListCount() throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		count=((Integer)sqlSession.selectOne("Test.board_count"));
		
		return count;
	}
	
	//게시판 조회수 증가
	public void boardHit(int board_num) throws Exception{
		sqlSession.update("Test.board_hit",board_num);
	}
	
	//게시판 상세 보기
	public BoardBean getBoardCont(int board_num) throws Exception{
		BoardBean board=(BoardBean) sqlSession.selectOne("Test.board_cont",board_num);
		return board;
	}
	
	//게시판 수정
	public void edit(HttpServletResponse response, BoardBean b) throws Exception{
		sqlSession.update("Test.board_edit",b); 
	}
	
	//게시판 삭제
	public void boardDelete(HttpServletResponse response, int board_num, String pwd) throws Exception{
		sqlSession.delete("Test.board_delete",board_num);
	}
	
	//답변글 레벨 증가
	public void refEdit(BoardBean b) throws Exception{
		System.out.println("refEdit");
		sqlSession.update("Test.board_level",b);
	}
	
	//답변글 저장
	public void boardReplyOk(BoardBean b) throws Exception{
		System.out.println("boardReplyOK");
		sqlSession.insert("Test.board_reply",b);
	}

}
