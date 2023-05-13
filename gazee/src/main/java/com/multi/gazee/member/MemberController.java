package com.multi.gazee.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller 
public class MemberController {
	
	@Autowired
	MemberDAO dao; 
	
}