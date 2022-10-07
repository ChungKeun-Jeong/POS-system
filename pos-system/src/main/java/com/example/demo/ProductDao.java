package com.example.demo;

import java.util.*;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class ProductDao {
    private Product product;
    private JdbcTemplate jdbcTemplate;
    
    
	public ProductDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    public Product selectByCode(String code) {
		List<Product> results = jdbcTemplate.query(
			"select * from PRODUCT where CODE = ?",
			new RowMapper<Product>() {
				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					Product product = new Product(
						rs.getString("CODE"),
						rs.getString("NAME"),
						rs.getInt("PRICE"),
                        rs.getInt("QUANTITY")
					);
					return product;
				}
			}, code
        );
		return results.isEmpty() ? null : results.get(0);
	}

    public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from PRODUCT", Integer.class);
		return count;
	}

    public void update(Product product) {
		jdbcTemplate.update(
			"update PRODUCT set NAME = ?, QUANTITY = ?, PRICE = ? where CODE = ?",
			product.getName(), product.getQuantity(), product.getPrice(), product.getCode());
	}

    public void insert(Product product) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
					"insert into PRODUCT (CODE, NAME, PRICE, QUANTITY) " + "values (?, ?, ?, ?)"
                );
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, product.getCode());
				pstmt.setString(2, product.getName());
				pstmt.setInt(3, product.getPrice());
                pstmt.setInt(4, product.getQuantity());
				
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		} );
	}

    public List<Product> selectAll() {
		List<Product> results = jdbcTemplate.query("select * from PRODUCT",
			(ResultSet rs, int rowNum) -> {
				Product product = new Product(
					rs.getString("CODE"),
					rs.getString("NAME"),
					rs.getInt("PRICE"),
					rs.getInt("QUANTITY")
				);
				return product;
			}
		);
		return results;
	}
    
    public void delete(Product product) {
		jdbcTemplate.update(
			"delete from PRODUCT where CODE = ?",
			product.getCode()
		);
	}
    
    public int getQuantityByCode(String code) {
        for (int i = 0; i < count(); i++) {
            product = selectAll().get(i);
            
            if (code.equals(product.getCode())) {
                return product.getQuantity();        
            }
        }
        return -1;
    }

    public void setQuantityByCode(String code, int quantity) {
        for (int i = 0; i < count(); i++) {
            product = selectAll().get(i);
            
            if (code.equals(product.getCode())) {
                product.setQuantity(quantity);        
                update(product);
                return;
            }
        }
    }

    public int getPriceByCode(String code) {
        for (int i = 0; i < count(); i++) {
            product = selectAll().get(i);
            
            if (code.equals(product.getCode())) {
                return product.getPrice();        
            }
        }
        return -1;
    }
	
    // 신제품 등록할 때 중복된 코드인지 확인
    public String checkCode(String code) {
        for (int i = 0; i < count(); i++) {
            product = selectAll().get(i);
            
            if (code.equals(product.getCode())) {
                return code;        
            }
        }
        return "code error";
	}

    // 제품 판매할 때 제품 이름을 입력하면 제품 코드로 변환
    public String nameToCode(String name) {
        for (int i = 0; i < count(); i++) {
            product = selectAll().get(i);
            
            if (name.equals(product.getName())) {
                return product.getCode();        
            }
        }
        return "name error";
    }

}
