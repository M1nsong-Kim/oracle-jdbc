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


@WebServlet("/member/modifyMember")
public class ModifyMemberController extends HttpServlet {
	
	private MemberService memberService;
	
	// 회원 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함

		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {	// 로그인하지 않았다면
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		// VIEW
		request.getRequestDispatcher("/WEB-INF/view/member/modifyMember.jsp").forward(request, response);
	}
	
	// 회원정보 수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함

		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {	// 로그인하지 않았다면
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");	// 한글 인코딩
		
		String changeMemberName = request.getParameter("changeMemberName");
		String memberPw = request.getParameter("memberPw");
		
		Member changeMember = loginMember;	// 기존 로그인 회원 정보에서
		changeMember.setMemberName(changeMemberName);	// 이름만 바뀜
		
		this.memberService = new MemberService();
		
		// 비밀번호 일치 확인
		boolean checkPw = memberService.getMemberPw(loginMember, memberPw);
		if(!checkPw) {	// 비밀번호가 일치하지 않는다면
			String msg = URLEncoder.encode("비밀번호를 정확하게 입력해 주세요.", "UTF-8");
			response.sendRedirect(request.getContextPath()+"/member/modifyMember?msg="+msg);
			return;
		}
		
		System.out.println("비밀번호 일치 확인 완료");
		
		// 회원정보 수정
		int row = memberService.modifyMember(loginMember, changeMember);
		
		if(row != 1){
			String msg = URLEncoder.encode("회원정보 변경에 실패했습니다", "UTF-8");
			response.sendRedirect(request.getContextPath()+"/member/modifyMember?msg="+msg);
			return;
		}
		
		System.out.println("회원정보 변경 성공");
		session.setAttribute("loginMember", changeMember);
		response.sendRedirect(request.getContextPath()+"/member/memberOne?memberId="+changeMember.getMemberId());
		// session에 수정된 회원 정보 저장 (날짜?)
	}

}