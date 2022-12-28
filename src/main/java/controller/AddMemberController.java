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

@WebServlet("/member/addMember")
public class AddMemberController extends HttpServlet {
	
	private MemberService memberService;

	// 회원가입 폼
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
		request.getRequestDispatcher("/WEB-INF/view/member/signup.jsp").forward(request, response);
	}

	// 회원가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 전에만 진입 가능
		HttpSession session = request.getSession();
		// loginMember : 로그인 전이면 null
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {	// 로그인 된 상태라면
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		request.setCharacterEncoding("UTF-8"); // 한글 인코딩
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);

		this.memberService = new MemberService();
		int row = memberService.signup(member);
		
		if(row != 1) {	// 회원가입 실패
			System.out.print("회원가입 실패");
			response.sendRedirect(request.getContextPath()+"/member/addMember");
			return;
		}
		
		// 회원가입 성공 -> 로그인 창으로
		System.out.print("회원가입 성공");
		response.sendRedirect(request.getContextPath()+"/member/login");
	}

}