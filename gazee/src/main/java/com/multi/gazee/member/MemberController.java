package com.multi.gazee.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller 
public class MemberController {
	
	@Autowired
	MemberDAO dao; 
	
	@RequestMapping("member/login")
	@ResponseBody
	public MemberVO login(MemberVO bag, HttpSession session) {
		MemberVO result = dao.login(bag);
		if(result == null){
			bag.setId("no");
			return bag;
		} else {
			session.setAttribute("id", result.getId());
			System.out.println(session);
			return result;
		}
	}
		
	@RequestMapping("member/insert")
	public String insert(MemberVO bag) {
		
		System.out.println(bag);
		dao.insert(bag);
		return "redirect:member.jsp";
	}
	
	@RequestMapping("member/delete")
	public void delete(String id) {
		System.out.println(id);
		dao.delete(id);
	}
	@RequestMapping("member/update")
	public void update(MemberVO bag) {
		System.out.println(bag);
		dao.update(bag);
	}
	@RequestMapping("member/one")
	public void one(String id, Model model) {
		System.out.println(id);
		MemberVO bag = dao.one(id);
		model.addAttribute("bag", bag);
	}

	@RequestMapping("member/one2")
	public void one2(String name, Model model) {
		System.out.println(name);
		MemberVO bag = dao.one2(name);
		model.addAttribute("bag", bag);
	}
	
	@RequestMapping("member/list")
	public void list(Model model) {
		List<MemberVO> list = dao.list();
		model.addAttribute("list", list);
	}
}