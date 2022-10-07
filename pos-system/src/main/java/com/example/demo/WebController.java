package com.example.demo;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private Login login;
	
	@Autowired
	private Staff staff;
	
	@Autowired
	private Manager manager;
	
	@Autowired
	private ReceiveProductDao receiveProductDao;
	
	@RequestMapping("/list")
    public String list() { return "list"; }
	
    @RequestMapping("/index")
    public String index() { return "index"; }
        
    @RequestMapping("/join")
    public String join(@RequestParam(value = "id") String id, @RequestParam(value = "password") String password, 
    		@RequestParam(value = "name") String name, @RequestParam(value = "position") String position) {
    	
    	// Member 객체 만들어서 DB에 insert함
    	String message = login.joinMember(id, password, name, position);
    	
    	if (message.equals("id error")) {
    		return "redirect:joinMember?idError"; 
    	}
    	else if (message.equals("position error")) {
    		return "redirect:joinMember?positionError";
    	}
    	
    	return "redirect:index?complete";
    }
    
    @RequestMapping("/joinMember")
    public String joinMember() { return "joinMember"; }
    
    @RequestMapping("/login")
    public String login() { return "login"; }
    
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public String log(@RequestParam(value = "id") String id, @RequestParam(value = "password") String password) {
    	String message = login.inputLogin(id, password);
    	if (message.equals("매니저")) {
    		return "redirect:manager";
    	}
    	else if (message.equals("직원")) {
    		return "redirect:staff";
    	}
    	else {
    		return "redirect:login?error";
    	}
    }
    
    @RequestMapping("/staff")
    public String staff(HttpServletRequest req, Model model) {
        List<Product> productList = productDao.selectAll();
        model.addAttribute("products", productList); 
        
        // 버튼 눌러서 장바구니 담을 때
        String code = req.getParameter("code");	 
        if (code != null) {
        	staff.choiceProduct(code);
        }
        
        // 이름 검색해서 장바구니 담을 때
        String searchProduct = req.getParameter("searchProduct");	
        if (searchProduct != null && !(searchProduct.trim().isEmpty())) {
        	String productCode = productDao.nameToCode(searchProduct);    	
        	staff.choiceProduct(productCode);
        }
        
        // 선택한 장바구니 제품 삭제
        String delete = req.getParameter("delete");
        if (delete != null) {
        	staff.deleteProduct(delete);       	
        }
        
        // 장바구니 전체 삭제
        String deleteAll = req.getParameter("deleteAll");
        if (deleteAll != null) {
        	cartDao.deleteAll();
        }
        
        String payCash = req.getParameter("payCash");
        if (payCash != null) {
        	staff.pay();
        	cartDao.deleteAll();
        	return "redirect:staff?cash";
        }
        
        String payCard = req.getParameter("payCard");
        if (payCard != null) {
        	staff.pay();
        	cartDao.deleteAll();
        	return "redirect:staff?card";
        }
        
        // 장바구니 수량 뺴기
        String minus = req.getParameter("minus");
        if (minus != null) {
        	Cart cart = cartDao.selectByCode(minus);
        	if (cart != null && cart.getQuantity() > 1) {
        		cart.setQuantity(cart.getQuantity() - 1);
        		cartDao.update(cart);
        	}
        }
        
        // 장바구니 수량 더하기
        String plus = req.getParameter("plus");
        if (plus != null) {
        	Cart cart = cartDao.selectByCode(plus);
        	if (cart != null) {
        		cart.setQuantity(cart.getQuantity() + 1);
        		cartDao.update(cart);
        	}
        }
        
        String logout = req.getParameter("logout");
        if (logout != null) {
        	cartDao.deleteAll();
        	return "redirect:login";
        	//response.sendRedirect("login");
        }
        
        List<Cart> cartList = cartDao.selectAll();  
        model.addAttribute("carts", cartList); 
        
        return "staff";
    }
    
    @RequestMapping("/manager")
    public String manager(HttpServletRequest req) {
    	String logout = req.getParameter("logout");
        if (logout != null) {
        	return "redirect:login";
        }
    	return "manager"; 
    }  
    
    @RequestMapping(value = "/memberInfo", method = RequestMethod.POST)
    public String memberInfo(Model model) {
    	List<Member> staffList = memberDao.selectStaff();
    	List<Member> managerList = memberDao.selectManager();
        model.addAttribute("staffs", staffList);
        model.addAttribute("managers", managerList);
    	
    	return "memberInfo"; 
    }  
    
    @RequestMapping(value = "/productInfo")
    public String productInfo(HttpServletRequest req, Model model) {
    	List<Product> productList = productDao.selectAll();
        model.addAttribute("products", productList);
                
        // 제품 삭제
        String delete = req.getParameter("delete");
        if (delete != null) {
        	Product product = productDao.selectByCode(delete);
        	if (product != null) productDao.delete(product);
        	return "redirect:productInfo?productDelete";
        }
        
    	return "productInfo"; 
    }  
    
    @RequestMapping("/updateProductInfo")
    public String updateProductInfo(HttpServletRequest req, Model model) {
    	String update = req.getParameter("update");
        if (update != null) {
        	Product product = productDao.selectByCode(update);
        	model.addAttribute("product", product);
        }
        
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        
        // 제품 정보 수정
        if (code != null && name != null && price != null) {
        	manager.updateProduct(code, name, Integer.parseInt(price));
            return "redirect:productInfo?productUpdate";
        }
        
    	return "updateProductInfo"; 
    }  
    
    @RequestMapping("/receiveProduct")
    public String receiveProduct(HttpServletRequest req) {
    	String code = req.getParameter("code");
        String quantity = req.getParameter("quantity");
        
        // 제품 입고
        if (code != null && quantity != null) {
        	int q = Integer.parseInt(quantity);
        	String message = manager.receiveProduct(code, q); 
        	
        	if (message.equals("error")) {
        		return "redirect:receiveProduct?error";
        	}
        	return "redirect:manager?receive";
        }
        
    	return "receiveProduct"; 
    }  
    
    @RequestMapping("/receiveProductInfo")
    public String receiveProductInfo(Model model) {
    	
    	// 제품 입고 내역
    	List<ReceiveProduct> receiveProductList = receiveProductDao.selectAll();
        model.addAttribute("receiveInfo", receiveProductList);
        
        // 제품명 찾기
        List<String> nameList = new ArrayList<String>();
        for (int i = 0; i < receiveProductList.size(); i++) {
        	ReceiveProduct receiveProduct = receiveProductList.get(i);
        	Product product = productDao.selectByCode(receiveProduct.getCode());
        	nameList.add(product.getName());
        }
        model.addAttribute("names", nameList);
        
    	return "receiveProductInfo"; 
    }  
    
    @RequestMapping("/newProduct")
    public String newProduct(HttpServletRequest req) {
    	String code = req.getParameter("code");
    	String name = req.getParameter("name");
    	String price = req.getParameter("price");
        
    	// 새로운 제품 등록
        if (code != null && name != null && price != null) {
        	int p = Integer.parseInt(price);
        	String message = manager.newProduct(code, name, p); 
        	
        	if (message.equals("error")) {
        		return "redirect:newProduct?error";
        	}
        	return "redirect:manager?new";
        }
        
    	return "newProduct"; 
    }  
    
    // 통계, 분석
    @RequestMapping("/statistics")
    public String statics(Model model) {
    	manager.statistics();
    	
    	List<String> dateList = manager.getDateList();
    	List<String> nameList = manager.getNameList();
    	List<Integer> quantityList = manager.getQuantityList();
    	List<Integer> sumList = manager.getSumList();
    	int todayTotalSales = manager.getTodayTotalSales();
    	String topSellingName = manager.getTopSellingName();
    	int topSellingQuantity = manager.getTopSellingQuantity();
    	String month = manager.getMonth();
    	String today = manager.getDate();
    	List<String> monthNameList = manager.getMonthNameList();
    	List<Integer> monthQuantityList = manager.getMonthQuantityList();
    	List<Integer> monthSumList = manager.getMonthSumList();
    	int monthTotalSales = manager.getMonthTotalSales();
    	String monthTopSellingName = manager.getMonthTopSellingName();
    	int monthTopSellingQuantity = manager.getMonthTopSellingQuantity();
    	
    	model.addAttribute("date", dateList);
    	model.addAttribute("name", nameList);
    	model.addAttribute("quantity", quantityList);
    	model.addAttribute("sum", sumList);
    	model.addAttribute("todayTotalSales", todayTotalSales);
    	model.addAttribute("topSellingName", topSellingName);
    	model.addAttribute("topSellingQuantity", topSellingQuantity);
    	model.addAttribute("month", month);
    	model.addAttribute("today", today);
    	model.addAttribute("monthName", monthNameList);
    	model.addAttribute("monthQuantity", monthQuantityList);
    	model.addAttribute("monthSum", monthSumList);
    	model.addAttribute("monthTotalSales", monthTotalSales);
    	model.addAttribute("monthTopSellingName", monthTopSellingName);
    	model.addAttribute("monthTopSellingQuantity", monthTopSellingQuantity);
    	
    	return "statistics"; 
    }  
}