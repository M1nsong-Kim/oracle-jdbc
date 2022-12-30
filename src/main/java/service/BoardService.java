package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.BoardDao;
import util.DBUtil;
import vo.Board;

public class BoardService {
	
	private BoardDao boardDao;
	
	// 글 목록
	public ArrayList<Board> getBoardListByPage(int currentPage, int rowPerPage) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Board> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endPage = beginRow + rowPerPage - 1;
			boardDao = new BoardDao();
			list = boardDao.selectBoardListByPage(conn, beginRow, endPage);
			conn.commit(); // DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // DBUtil.class에서 conn.setAutoCommit(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 글 개수 (마지막 페이지 구하기)
	public int getCountBoardList() {
		int count = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			boardDao = new BoardDao();
			count = boardDao.selectCountBoardList(conn);
			conn.commit(); // DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // DBUtil.class에서 conn.setAutoCommit(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	// 글쓰기
	public int addBoard(Board board) {
		int row = 0;
		this.boardDao = new BoardDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			row = boardDao.insertBoard(conn, board);
			conn.commit();	// DBUtil에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback();	// DBUtil에서 conn.setAutoCommit(false);
				// 하나라도 실패하면 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DBUtil.close(null, null, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 글 상세보기
	public Board getBoard(int boardNo) {
		Board board = new Board();
		this.boardDao = new BoardDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			board = boardDao.selectBoardOne(conn, boardNo);
			conn.commit();	// DBUtil에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback();	// DBUtil에서 conn.setAutoCommit(false);
				// 하나라도 실패하면 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DBUtil.close(null, null, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return board;
	}
	
	// 글 수정
	public int modifyBoard(Board board) {
		int row = 0;
		this.boardDao = new BoardDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			row = boardDao.updateBoard(conn, board);
			conn.commit();	// DBUtil에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback();	// DBUtil에서 conn.setAutoCommit(false);
				// 하나라도 실패하면 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DBUtil.close(null, null, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 글 삭제
	public int removeBoard(int boardNo) {
		int row = 0;
		this.boardDao = new BoardDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			row = boardDao.deleteBoard(conn, boardNo);
			conn.commit();	// DBUtil에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback();	// DBUtil에서 conn.setAutoCommit(false);
				// 하나라도 실패하면 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DBUtil.close(null, null, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}