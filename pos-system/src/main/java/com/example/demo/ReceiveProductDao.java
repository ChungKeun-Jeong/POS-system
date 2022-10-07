package com.example.demo;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class ReceiveProductDao {
    private JdbcTemplate jdbcTemplate;

	public ReceiveProductDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    public ReceiveProduct selectByCode(String code) {
		List<ReceiveProduct> results = jdbcTemplate.query(
			"select * from RECEIVEPRODUCT where CODE = ?",
			new RowMapper<ReceiveProduct>() {
				@Override
				public ReceiveProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
					ReceiveProduct receiveProduct = new ReceiveProduct(
						rs.getString("DATE"),
						rs.getString("CODE"),
                        rs.getString("RECEIVENUMBER"),
                        rs.getInt("PLUSQUANTITY")
					);
					return receiveProduct;
				}
			}, code
        );
		return results.isEmpty() ? null : results.get(0);
	}

    public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from RECEIVEPRODUCT", Integer.class);
		return count;
	}

    public void update(ReceiveProduct receiveProduct) {
		jdbcTemplate.update(
			"update RECEIVEPRODUCT set PLUSQUANTITY = ? where RECEIVENUMBER = ?",
			receiveProduct.getPlusQuantity(), receiveProduct.getReceiveNumber());
	}

    public void insert(ReceiveProduct receiveProduct) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
					"insert into RECEIVEPRODUCT (DATE, CODE, RECEIVENUMBER, PLUSQUANTITY) " + "values (?, ?, ?, ?)"
                );
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, receiveProduct.getDate());
				pstmt.setString(2, receiveProduct.getCode());
				pstmt.setString(3, receiveProduct.getReceiveNumber());
                pstmt.setInt(4, receiveProduct.getPlusQuantity());
				
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		} );
	}

    public List<ReceiveProduct> selectAll() {
		List<ReceiveProduct> results = jdbcTemplate.query("select * from RECEIVEPRODUCT",
			(ResultSet rs, int rowNum) -> {
				ReceiveProduct receiveProduct = new ReceiveProduct(
					rs.getString("DATE"),
					rs.getString("CODE"),
					rs.getString("RECEIVENUMBER"),
					rs.getInt("PLUSQUANTITY")
				);
				return receiveProduct;
			}
		);
		return results;
	}

    // 제품 입고 정보 등록
    public void receiveProductInfo(String code, int plusQuantity) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
        String date = now.format(formatter);

        ReceiveProduct receiveProduct = new ReceiveProduct(date, code, code+date, plusQuantity);
        insert(receiveProduct);
    }
}
