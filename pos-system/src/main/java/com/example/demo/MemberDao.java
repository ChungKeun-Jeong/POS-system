package com.example.demo;

import java.util.*;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class MemberDao {
    private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    public Member selectById(String id) {
		List<Member> results = jdbcTemplate.query(
			"select * from MEMBER where ID = ?",
			new RowMapper<Member>() {
				@Override
				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Member member = new Member(
						rs.getString("ID"),
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
                        rs.getString("POSITION")
					);
					return member;
				}
			}, id
        );
		return results.isEmpty() ? null : results.get(0);
	}

    public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);
		return count;
	}

    public void insert(Member member) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
					"insert into MEMBER (ID, PASSWORD, NAME, POSITION) " + "values (?, ?, ?, ?)"
                );
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
                pstmt.setString(4, member.getPosition());
				
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		} );
	}

    public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER",
			(ResultSet rs, int rowNum) -> {
				Member member = new Member(
					rs.getString("ID"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getString("POSITION")
				);
				return member;
			}
		);
		return results;
	}
    
    public List<Member> selectStaff() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where POSITION = '직원'",
			(ResultSet rs, int rowNum) -> {
				Member member = new Member(
					rs.getString("ID"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getString("POSITION")
				);
				return member;
			}
		);
		return results;
	}
    
    public List<Member> selectManager() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where POSITION = '매니저'",
			(ResultSet rs, int rowNum) -> {
				Member member = new Member(
					rs.getString("ID"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getString("POSITION")
				);
				return member;
			}
		);
		return results;
	}
    
    // 로그인 정보 맞는지 확인
    public String checkLogin(String id, String password) {
        String saveId = "";
        String savePassword = "";
        
        for (int i = 0; i < count(); i++) {
            saveId = selectAll().get(i).getId();
            savePassword = selectAll().get(i).getPassword();

            if (id.equals(saveId)) {
                if (password.equals(savePassword)) return id;
                else return "password error";
            }
        }
        return "id error";
	}

    // 회원가입할 때 중복 id인지 확인
    public String checkId(String id) {
        String saveId = "";

        for (int i = 0; i < count(); i++) {
            saveId = selectAll().get(i).getId();

            if (id.equals(saveId)) return "id already exists";
        }
        return id;
    }

}
