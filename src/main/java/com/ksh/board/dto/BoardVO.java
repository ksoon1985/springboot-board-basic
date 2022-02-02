package com.ksh.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BoardVO {
	private int rr;
	private int num;
	private int readcnt;
	private int ref;
	private int re_step;
	private int re_level;
	private String writer;
	private String subject;
	private String email;
	private String content;
	private String passwd;
	private String reg_date;
	private String attachNm;
	private int fileNo;
	private long fileSize;
	private String orgFileNm;
}