package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtil;
import vo.Member;

public class MemberDao {
	// 로그인
	public Member selectMemberByIdAndPw(Connection conn, Member paramMember) throws Exception {
		Member resultMember = null;
		
		String sql = "SELECT member_id memberId\r\n"
				+ "        , member_pw memberPw\r\n"
				+ "        , member_name memberName\r\n"
				+ "        , updatedate\r\n"
				+ "        , createdate\r\n"
				+ "FROM member\r\n"
				+ "WHERE member_id = ? AND member_pw = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, paramMember.getMemberId());
		stmt.setString(2, paramMember.getMemberPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			resultMember = new Member();
			resultMember.setMemberId(rs.getString("memberId"));
			resultMember.setMemberPw(rs.getString("memberPw"));
			resultMember.setMemberName(rs.getString("memberName"));
			resultMember.setUpdatedate(rs.getString("updatedate"));
			resultMember.setCreatedate(rs.getString("createdate"));
		}
		
		return resultMember;
	}
	
	// 회원가입
	public int insertMember(Connection conn, Member paramMember) throws Exception{
		int row = 0;
		
		String sql = "INSERT INTO member(member_id\r\n"
				+ "                    , member_pw\r\n"
				+ "                    , member_name\r\n"
				+ "                    , updatedate\r\n"
				+ "                    , createdate\r\n"
				+ "                    )\r\n"
				+ "VALUES( ? \r\n"
				+ "        , ? \r\n"
				+ "        , ? \r\n"
				+ "        , sysdate\r\n"
				+ "        , sysdate\r\n"
				+ "        )";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, paramMember.getMemberId());
		stmt.setString(2, paramMember.getMemberPw());
		stmt.setString(3, paramMember.getMemberName());
		
		row = stmt.executeUpdate();
		// 회원가입 성공 -> 1 반환
		return row;
	}
	
	// 비밀번호 일치 확인
	public boolean selectMemberPw(Connection conn, Member loginMember, String memberPw) throws Exception{
		boolean check = false;
		
		DBUtil dbUtil = new DBUtil();
		String sql = "SELECT * FROM member WHERE member_id = ? AND member_pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, loginMember.getMemberId());
		stmt.setString(2, memberPw);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {	// 비밀번호가 일치한다면
			check = true;
		}

		return check;
	}
	
	// 회원정보 수정 (이름)
	public int updateMember(Connection conn, Member loginMember, Member changeMember) throws Exception{
		int row = 0;
		
		String sql = "UPDATE member\r\n"
				+ "SET member_name = ? \r\n"
				+ "    , updatedate = sysdate\r\n"
				+ "WHERE member_id = ? ";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, changeMember.getMemberName());
		stmt.setString(2, loginMember.getMemberId());
		
		row = stmt.executeUpdate();
		// 정보수정 성공 -> 1 반환
		return row;
	}
	
	// 회원탈퇴
	public int deleteMember(Connection conn, Member member) throws Exception{
		int row = 0;
		
		String sql = "DELETE FROM member\r\n"
				+ "WHERE member_id = ? ";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		
		row = stmt.executeUpdate();
		// 회원탈퇴 성공 -> 1 반환
		return row;
	}
}
