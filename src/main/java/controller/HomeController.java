package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * 로그인 전
		 * 1) 회원가입
		 * 2) 로그인
		 */
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		 /* 로그인 후
		 * 1) 로그아웃
		 * 2) 회원정보
		 * 3) 게시판리스트
		 */
		
		// View
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}
}