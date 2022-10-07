package com.example.demo;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

public class Manager {
    private String message;
    private String today;
    private ArrayList<String> dateList = new ArrayList<String>();
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<Integer> quantityList = new ArrayList<Integer>();
    private ArrayList<Integer> sumList = new ArrayList<Integer>();
    private int todayTotalSales = 0;
    private String topSellingName = "";
    private int topSellingQuantity = 0;
    
    private ArrayList<String> monthNameList = new ArrayList<String>();
    private ArrayList<Integer> monthQuantityList = new ArrayList<Integer>();
    private ArrayList<Integer> monthSumList = new ArrayList<Integer>();
    private int monthTotalSales = 0;
    private String monthTopSellingName = "";
    private int monthTopSellingQuantity = 0;
    
    private ProductDao productDao;
    private ReceiveProductDao receiveProductDao;
    private PayDao payDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
    @Autowired
    public void setReceiveProductDao(ReceiveProductDao receiveProductDao) {
		this.receiveProductDao = receiveProductDao;
	}
    @Autowired
    public void setPayDao(PayDao payDao) {
		this.payDao = payDao;
	} 


    // 제품 정보 수정
    public void updateProduct(String code, String name, int price) {
    	Product product = productDao.selectByCode(code);
    	product.setName(name);
        product.setPrice(price);
        productDao.update(product);
    }
    
    // 제품 입고
    public String receiveProduct(String code, int plusQuantity) {
        message = productDao.checkCode(code);
        if (message.equals("code error")) {
            return "error";
        } 
        
        Product product = productDao.selectByCode(code);
        int quantity = product.getQuantity() + plusQuantity;
        product.setQuantity(quantity);   
        productDao.update(product);
        
        receiveProductDao.receiveProductInfo(code, plusQuantity);   // 입고 정보 등록

        return "ok";
    }

    // 신제품 등록
    public String newProduct(String code, String name, int price) {
        if (code.equals(productDao.checkCode(code))) {
        	return "error";
        }
        
        Product product = new Product(code, name, price, 0);
        productDao.insert(product);
        
        return "ok";
    }

    // 통계 
    public void statistics() {
        String date = "";
        String name = "";
        int quantity = 0;
        int sum = 0;
        boolean addFinish = false;

        dateList.clear();
        nameList.clear();
        quantityList.clear();
        sumList.clear();
        monthNameList.clear();
        monthQuantityList.clear();
        monthSumList.clear();
        todayTotalSales = 0;
        topSellingQuantity = 0;
        monthTotalSales = 0;
        monthTopSellingQuantity = 0;
        
        // DB 판매 정보 가져와서 분류하기
        for (int i = 0; i < payDao.count(); i++) {
            Pay pay = payDao.selectAll().get(i);
            
            date = pay.getDate().substring(0, 4);
            name = pay.getName();
            quantity = pay.getQuantity();
            sum = pay.getTotalPrice();

            for (int j = 0; j < nameList.size(); j++) {
                // 같은 날에 같은 제품을 판매했다면 둘을 합침
                if (dateList.get(j).equals(date) && nameList.get(j).equals(name)) {
                    quantity += quantityList.get(j);
                    sum += sumList.get(j);
                    quantityList.set(j, quantity);
                    sumList.set(j, sum);

                    addFinish = true;
                    break;
                }
            }
            if (!addFinish) {
                dateList.add(date);
                nameList.add(name);
                quantityList.add(quantity);
                sumList.add(sum);
            }
            addFinish = false;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
        today = now.format(formatter);
                
        // 오늘 매출 통계
        for (int i = 0; i < nameList.size(); i++) {
            if (dateList.get(i).equals(today)) {    // 판매 날짜가 오늘인 것만 
                todayTotalSales += sumList.get(i);
                if (quantityList.get(i) > topSellingQuantity) {
                    topSellingQuantity = quantityList.get(i);
                    topSellingName = nameList.get(i);
                }
            }
        }


        // 이번달 매출 통계        
        for (int i = 0; i < nameList.size(); i++) {
            for (int j = 0; j < monthNameList.size(); j++) {
                // 같은 달에 같은 제품을 판매했다면 둘을 합침
                if (dateList.get(i).substring(1,2).equals(today.substring(1,2)) && monthNameList.get(j).equals(nameList.get(i))) {
                    monthQuantityList.set(j, monthQuantityList.get(j) + quantityList.get(i));
                    monthSumList.set(j, monthSumList.get(j) + sumList.get(i));

                    addFinish = true;
                    break;
                }
            }
            if (dateList.get(i).substring(1,2).equals(today.substring(1,2)) && !addFinish) {
            	monthNameList.add(nameList.get(i));
            	monthQuantityList.add(quantityList.get(i));
            	monthSumList.add(sumList.get(i));
            }
            addFinish = false;
        }

        // 이번 달 최다 판매 제품, 총 매출액 찾기
        for (int i = 0; i < monthNameList.size(); i++) {
            monthTotalSales += monthSumList.get(i);
            if (monthQuantityList.get(i) > monthTopSellingQuantity) {
                monthTopSellingQuantity = monthQuantityList.get(i);
                monthTopSellingName = monthNameList.get(i);
            }
        }
    }

    public List<String> getDateList() {
    	return dateList;
    }
    public List<String> getNameList() {
    	return nameList;
    }
    public List<Integer> getQuantityList() {
    	return quantityList;
    }
    public List<Integer> getSumList() {
    	return sumList;
    }
    public int getTodayTotalSales() {
    	return todayTotalSales;
    }
    public String getTopSellingName() {
    	return topSellingName;
    }
    public int getTopSellingQuantity() {
    	return topSellingQuantity;
    }
    public String getDate() {
    	return today;
    }
    public String getMonth() {
    	return today.substring(1,2);
    }
    public List<String> getMonthNameList() {
    	return monthNameList;
    }
    public List<Integer> getMonthQuantityList() {
    	return monthQuantityList;
    }
    public List<Integer> getMonthSumList() {
    	return monthSumList;
    }
    public int getMonthTotalSales() {
    	return monthTotalSales;
    }
    public String getMonthTopSellingName() {
    	return monthTopSellingName;
    }
    public int getMonthTopSellingQuantity() {
    	return monthTopSellingQuantity;
    }
}
