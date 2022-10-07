package com.example.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.tomcat.jdbc.pool.DataSource;


@Configuration
public class JavaConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/pos?characterEncoding=utf8&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("1234");
        return ds;
    }
    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
    @Bean
    public ProductDao productDao() {
        return new ProductDao(dataSource());
    }
    @Bean
    public CartDao cartDao() {
        return new CartDao(dataSource());
    }
    @Bean
    public ReceiveProductDao receiveProductDao() {
        return new ReceiveProductDao(dataSource());
    }
    @Bean
    public PayDao payDao() {
        return new PayDao(dataSource());
    }
    @Bean
    public Manager manager() {
        Manager manager = new Manager();
        manager.setProductDao(productDao());
        manager.setReceiveProductDao(receiveProductDao());
        manager.setPayDao(payDao());
        return manager;
    }
    @Bean
    public Staff staff() {
        Staff staff = new Staff();
        staff.setProductDao(productDao());
        staff.setCartDao(cartDao());
        staff.setPayDao(payDao());
        return staff;
    }
    @Bean
    public Login login() {
        Login login = new Login();
        login.setMemberDao(memberDao());
        return login;
    }
}   
