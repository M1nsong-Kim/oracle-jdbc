package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDao;
import util.DBUtil;
import vo.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	// 로그인
	public Member login(Member member) {
		this.memberDao = new MemberDao();
		Connection conn = null;
		Member loginMember = new Member();
		try {
			conn = DBUtil.getConnection();
			loginMember = memberDao.selectMemberByIdAndPw(conn, member);
			conn.commit(); // DBUtil에서 conn.setAutoCommit(false);
		} catch(Exception e) {
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
		return loginMember;
	}
	
	// 회원가입
	public int signup(Member member) {
		int check = 0;
		this.memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			check = memberDao.insertMember(conn, member);
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
		return check;
	}
	
	// 비밀번호 일치 확인
	public boolean getMemberPw(Member loginMember, String memberPw) {
		boolean check = false;
		this.memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			check = memberDao.selectMemberPw(conn, loginMember, memberPw);
			conn.commit(); // DBUtil에서 conn.setAutoCommit(false);
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
		return check;
	}
	
	// 회원정보 수정 (이름)
	public int modifyMember(Member loginMember, Member changeMember) {
		int row = 0;
		this.memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			row = memberDao.updateMember(conn, loginMember, changeMember);
			conn.commit(); // DBUtil에서 conn.setAutoCommit(false);
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
		
		return row;
	}
	
	// 회원탈퇴
	public int removeMember(Member loginMember) {
		int row = 0;
		this.memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			row = memberDao.deleteMember(conn, loginMember);
			conn.commit(); // DBUtil에서 conn.setAutoCommit(false);
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
}
