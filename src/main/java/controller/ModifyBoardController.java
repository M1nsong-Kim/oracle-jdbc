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


@WebServlet("/board/modifyBoard")
public class ModifyBoardController extends HttpServlet {
	
	BoardService boardService;
	
	// 글 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함

		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {	// 로그인하지 않았다면
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}

		// 원래 글
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		this.boardService = new BoardService();
		Board board = boardService.getBoard(boardNo);
		request.setAttribute("board", board);
				
		// View
		request.getRequestDispatcher("/WEB-INF/view/board/modifyBoard.jsp").forward(request, response);
	}
	// 글 수정 액션
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
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		
		this.boardService = new BoardService();
		int row = boardService.modifyBoard(board);
		
		if(row != 1) {	// 글 수정 실패
			System.out.println("글 수정 실패");
			response.sendRedirect(request.getContextPath()+"/board/modifyBoard?boardNo="+boardNo);
			return;
		}
		
		// 글 수정 성공 -> 글 목록으로
		System.out.print("글 수정 성공");
		response.sendRedirect(request.getContextPath()+"/board/boardList");
	}

}