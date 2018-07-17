package model;

import java.util.Date;

import lombok.Data;


@Data
public class BoardBean {
	private int board_num;
	private String board_name;
	private String board_pass;
	private String board_subject; //글제목
	private String board_content; //글내용
	private int board_re_ref; //글그룹번호
	private int board_re_lev; //화면에 보이는 답변글 위치번호
	private int board_re_seq; //답변글 레벨 순서
	private int board_readcount; //조회수
	//private String board_date; //글 등록날짜
	private Date board_date; //글 등록날짜
	
}
