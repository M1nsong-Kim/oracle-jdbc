package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Board;

public class BoardDao {
	// 검색 추가
	public ArrayList<Board> selectBoardListByPage(Connection conn, int beginRow, int endRow) throws Exception {
		ArrayList<Board> list = new ArrayList<Board>();
		String sql = "SELECT board_no boardNo, board_title boardTitle, createdate"
				+ " FROM (SELECT rownum rnum, board_no, board_title, createdate"
				+ "			FROM (SELECT board_no, board_title, createdate"
				+ "					FROM board ORDER BY board_no DESC))"
				+ " WHERE rnum BETWEEN ? AND ?"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Board b = new Board();
			b.setBoardNo(rs.getInt("boardNo"));
			b.setBoardTitle(rs.getString("boardTitle"));
			b.setCreatedate(rs.getString("createdate"));
			list.add(b);
		}
		return list;
	}
	
	// 글쓰기
	public int insertBoard(Connection conn, Board board) throws Exception {
		int row = 0;
		
		String sql = "INSERT INTO board(board_no\r\n"
				+ "                    , board_title\r\n"
				+ "                    , board_content\r\n"
				+ "                    , member_id\r\n"
				+ "                    , updatedate\r\n"
				+ "                    , createdate)\r\n"
				+ "VALUES (board_seq.nextval\r\n"
				+ "        , ? \r\n"
				+ "        , ? \r\n"
				+ "        , ? \r\n"
				+ "        , sysdate\r\n"
				+ "        , sysdate)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, board.getBoardTitle());
		stmt.setString(2, board.getBoardContent());
		stmt.setString(3, board.getMemberId());
		
		row = stmt.executeUpdate();
		// 글쓰기 성공 -> 1 반환
		return row;
	}
	
	// 글 상세보기
	public Board selectBoardOne(Connection conn, int boardNo) throws Exception{
		Board board = null;
		
		String sql = "SELECT board_no boardNo\r\n"
				+ "        , board_title boardTitle\r\n"
				+ "        , board_content boardContent\r\n"
				+ "        , member_id memberId\r\n"
				+ "        , updatedate\r\n"
				+ "        , createdate\r\n"
				+ "FROM board\r\n"
				+ "WHERE boardNo = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, boardNo);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			board = new Board();
			board.setBoardNo(rs.getInt("boardNo"));
			board.setBoardTitle(rs.getString("boardTitle"));
			board.setBoardContent(rs.getString("boardContent"));
			board.setMemberId(rs.getString("memberId"));
			board.setUpdatedate(rs.getString("updatedate"));
			board.setCreatedate(rs.getString("createdate"));
		}
		return board;
	}
}