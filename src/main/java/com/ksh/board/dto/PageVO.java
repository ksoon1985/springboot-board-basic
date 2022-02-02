package com.ksh.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
	int currentPage; 			
	int linePerPage = 10; 		
	int allCount; 				 
	int allPage; 				 
	int pageBlock = 5;			
	int currPageBlock;			
	
	int startPage;				
	int endPage;				
	
}
