package com.example.demo;

import java.util.*;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class CartDao {
	private Cart cart;
    private JdbcTemplate jdbcTemplate;
    
	public CartDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
    public Cart selectByCode(String code) {
		List<Cart> results = jdbcTemplate.query(
			"select * from CART where CODE = ?",
			new RowMapper<Cart>() {
				@Override
				public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
					Cart cart = new Cart(
						rs.getString("CODE"),
						rs.getString("NAME"),
						rs.getInt("PRICE"),
                        rs.getInt("QUANTITY")
					);
					return cart;
				}
			}, code
        );
		return results.isEmpty() ? null : results.get(0);
	}

    public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from CART", Integer.class);
		return count;
	}

    public void update(Cart cart) {
		jdbcTemplate.update(
			"update CART set QUANTITY = ? where CODE = ?",
			cart.getQuantity(), cart.getCode());
	}
    
    public void deleteAll() {
		jdbcTemplate.update("delete from CART");
	}
    
    public void delete(Cart cart) {
		jdbcTemplate.update(
			"delete from CART where CODE = ?",
			cart.getCode()
		);
	}

    public void insert(Cart cart) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
					"insert into CART (CODE, NAME, PRICE, QUANTITY) " + "values (?, ?, ?, ?)"
                );
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, cart.getCode());
				pstmt.setString(2, cart.getName());
				pstmt.setInt(3, cart.getPrice());
                pstmt.setInt(4, cart.getQuantity());
				
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		} );
	}

    public List<Cart> selectAll() {
		List<Cart> results = jdbcTemplate.query("select * from CART",
			(ResultSet rs, int rowNum) -> {
				Cart cart = new Cart(
					rs.getString("CODE"),
					rs.getString("NAME"),
					rs.getInt("PRICE"),
					rs.getInt("QUANTITY")
				);
				return cart;
			}
		);
		return results;
	}
    
    public String checkCode(String code) {
        for (int i = 0; i < count(); i++) {
            cart = selectAll().get(i);
            
            if (code.equals(cart.getCode())) {
                return code;        
            }
        }
        return "code error";
	}
}
