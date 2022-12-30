package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.Board;
import vo.Member;

@WebServlet("/board/boardList")
public class BoardListController extends HttpServlet {
	private BoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession();	// HttpSession으로 받아야 함
	
		// loginMember : 로그인 전이면 null값
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {	// 로그인하지 않았다면
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		this.boardService = new BoardService();
		
		int rowPerPage = 10;	// 한 페이지당 보여줄 글 개수
	    int beginRow = (currentPage-1)*rowPerPage + 1;
	    int pageList = 10; // 페이지 10개씩 보여줌
	    int startPage = ((currentPage-1)/pageList)*pageList+1;    // n1
	    int endRow = startPage + pageList - 1;    // (n+1)0
	    int lastPage = (int)Math.ceil(boardService.getCountBoardList()/(double)rowPerPage);
		
		ArrayList<Board> list = boardService.getBoardListByPage(currentPage, rowPerPage);
		request.setAttribute("boardList", list);
		request.setAttribute("currentPage", currentPage); // view에서 필요
		request.setAttribute("startPage", startPage); // view에서 필요
		request.setAttribute("endRow", endRow); // view에서 필요
		request.setAttribute("lastPage", lastPage); // view에서 필요
		
		request.getRequestDispatcher("/WEB-INF/view/board/boardList.jsp").forward(request, response);
	}
}