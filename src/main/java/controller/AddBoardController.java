package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.*;


@WebServlet("/board/addBoard")
public class AddBoardController extends HttpServlet {
	
	BoardService boardService;
	
	// 글쓰기 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함

		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {	// 로그인하지 않았다면
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		// View
		request.getRequestDispatcher("/WEB-INF/view/board/addBoard.jsp").forward(request, response);
	}
	// 글쓰기 액션
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
		
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String memberId = loginMember.getMemberId();
		
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setMemberId(memberId);
		
		
		this.boardService = new BoardService();
		int row = boardService.addBoard(board);
		
		if(row != 1) {	// 글쓰기 실패
			System.out.println("글쓰기 실패");
			response.sendRedirect(request.getContextPath()+"/board/addBoard");
			return;
		}
		
		// 글쓰기 성공 -> 글 목록으로
		System.out.print("글쓰기 성공");
		response.sendRedirect(request.getContextPath()+"/board/boardList");
	}

}