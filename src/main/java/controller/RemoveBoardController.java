package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.Member;


@WebServlet("/board/removeBoard")
public class RemoveBoardController extends HttpServlet {
	
	BoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함

		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {	// 로그인하지 않았다면
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		this.boardService = new BoardService();
		int row = boardService.removeBoard(boardNo);
		
		if(row != 1) {	// 글 삭제 실패
			System.out.println("글 삭제 실패");
			response.sendRedirect(request.getContextPath()+"/board/boardOne?boardNo="+boardNo);
			return;
		}
		
		// 글 삭제 성공 -> 글 목록으로
		System.out.print("글 삭제 성공");
		response.sendRedirect(request.getContextPath()+"/board/boardList");
	}
}