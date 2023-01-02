package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;


@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {
	
	MemberService memberService;
	
	// 회원 탈퇴 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함

		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {	// 로그인하지 않았다면
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		String msg = "-1";
		if(request.getParameter("msg") != null) {
			msg = request.getParameter("msg");
		}
		request.setAttribute("msg", msg);
		
		// VIEW
		request.getRequestDispatcher("/WEB-INF/view/member/removeMember.jsp").forward(request, response);
	}

	// 회원 탈퇴 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함

		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {	// 로그인하지 않았다면
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}

		String memberPw = request.getParameter("memberPw");

		this.memberService = new MemberService();
		
		// 비밀번호 일치 확인
		boolean checkPw = memberService.getMemberPw(loginMember, memberPw);
		if(!checkPw) {	// 비밀번호가 일치하지 않는다면
			System.out.println("비밀번호가 일치하지 않습니다");
			String msg = URLEncoder.encode("비밀번호를 정확하게 입력해 주세요.", "UTF-8");
			response.sendRedirect(request.getContextPath()+"/member/removeMember?msg="+msg);
			return;
		}
		
		System.out.println("비밀번호 일치 확인 완료");
		
		// 회원 데이터 삭제
		int row = memberService.removeMember(loginMember);
		if(row != 1) {
			String msg = URLEncoder.encode("회원탈퇴에 실패했습니다", "UTF-8");
			response.sendRedirect(request.getContextPath()+"/member/removeMember?msg="+msg);
			return;
		}
		
		System.out.println("회원탈퇴 성공");
		// DB에서 삭제 후 로그아웃창 -> session에서도 사라짐
		response.sendRedirect(request.getContextPath()+"/member/logout");
	}

}