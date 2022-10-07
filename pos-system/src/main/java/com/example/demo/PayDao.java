package com.example.demo;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class PayDao {
    private JdbcTemplate jdbcTemplate;
    private int orderNumber = 0;

	public PayDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    public Pay selectByCode(String code) {
		List<Pay> results = jdbcTemplate.query(
			"select * from PAY where CODE = ?",
			new RowMapper<Pay>() {
				@Override
				public Pay mapRow(ResultSet rs, int rowNum) throws SQLException {
					Pay pay = new Pay(
						rs.getString("DATE"),
						rs.getString("NAME"),
                        rs.getString("CODE"),
                        rs.getInt("QUANTITY"),
                        rs.getInt("PRICE"),
                        rs.getInt("TOTALPRICE"),
                        rs.getString("ORDERNUMBER")
					);
					return pay;
				}
			}, code
        );
		return results.isEmpty() ? null : results.get(0);
	}

    public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from PAY", Integer.class);
		return count;
	}

    public void insert(Pay pay) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
				    "insert into PAY (DATE, NAME, CODE, QUANTITY, PRICE, TOTALPRICE, ORDERNUMBER) " + "values (?,?,?,?,?,?,?)"
                );
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, pay.getDate());
				pstmt.setString(2, pay.getName());
				pstmt.setString(3, pay.getCode());
                pstmt.setInt(4, pay.getQuantity());
                pstmt.setInt(5, pay.getPrice());
                pstmt.setInt(6, pay.getTotalPrice());
                pstmt.setString(7, pay.getOrderNumber());
		
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		} );
	}

    public List<Pay> selectAll() {
		List<Pay> results = jdbcTemplate.query("select * from PAY",
			(ResultSet rs, int rowNum) -> {
				Pay pay = new Pay(
					rs.getString("DATE"),
					rs.getString("NAME"),
                    rs.getString("CODE"),
                    rs.getInt("QUANTITY"),
                    rs.getInt("PRICE"),
                    rs.getInt("TOTALPRICE"),
                    rs.getString("ORDERNUMBER")
				);
				return pay;
			}
		);
		return results;
	}
    
    // 제품 판매 정보 등록
    public void payInfo(String name, String code, int quantity, int price, int totalPrice) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd - HH:mm");
        String date = now.format(formatter);

        Pay pay = new Pay(date, name, code, quantity, price, totalPrice, date+code+orderNumber);
        insert(pay);
        
        orderNumber++;
    }
}
