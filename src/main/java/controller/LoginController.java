package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	
	private MemberService memberService;
	
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 전에만 진입 가능
		HttpSession session = request.getSession();
		// loginMember : 로그인 전이면 null
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {	// 로그인 된 상태라면
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		// View
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
	}
	

	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함
		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {	// 로그인된 상태라면
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		
		Member paramMember = new Member();	// request 파라미터값으로 바인딩
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		
		this.memberService = new MemberService();
		Member returnMember = memberService.login(paramMember);
		
		if(returnMember == null) {	// 로그인 실패
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		// 로그인 성공이라면 session에 값 넣기
		System.out.print("로그인 성공");
		session.setAttribute("loginMember", returnMember);
		response.sendRedirect(request.getContextPath()+"/home");
	}

}