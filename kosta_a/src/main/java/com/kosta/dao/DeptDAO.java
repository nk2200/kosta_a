package com.kosta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.kosta.db.ConnectionProvider;
import com.kosta.vo.DeptVO;


public class DeptDAO {
	public int delete(int dno) {
		int re = -1;
		String sql = "delete dept where dno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception e) {
			System.out.println("delete dao 예외 => "+e);
		}
		return re;
	}
	
	public int update(DeptVO vo) {
		int re = -1;
		String sql = "update dept set dname=?,dloc=? where dno=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDname());
			pstmt.setString(2, vo.getDloc());
			pstmt.setInt(3, vo.getDno());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception e) {
			System.out.println("update dao 예외 => "+e);
		}
		return re;
	}
	
	public int insert(DeptVO vo) {
		int re = -1;
		String sql = "insert into dept values(?,?,?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getDno());
			pstmt.setString(2, vo.getDname());
			pstmt.setString(3, vo.getDloc());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception e) {
			System.out.println("insert dao 예외 => "+e);
		}
		return re;
	}
	
	public ArrayList<DeptVO> findAll(){
		
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();
		try {
			String sql = "select * from dept order by dno";
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new DeptVO(rs.getInt(1), rs.getString(2), rs.getString(3)));				
			}
			ConnectionProvider.close(rs, stmt, conn);
		}catch (Exception e) {
			System.out.println("dao findAll 예외 ==> "+e.getMessage());
		}
		return list;
	}
}
