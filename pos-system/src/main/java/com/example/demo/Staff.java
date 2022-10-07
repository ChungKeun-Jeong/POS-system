package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;


public class Staff {
    private ProductDao productDao;
    private PayDao payDao;
    private CartDao cartDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
    @Autowired
    public void setPayDao(PayDao payDao) {
		this.payDao = payDao;
	}
    @Autowired
    public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}


    public void choiceProduct(String code) {
    	
    	// 장바구니에 담겨있으면 개수 늘림
    	if (code.equals(cartDao.checkCode(code))) {
    		Cart cart = cartDao.selectByCode(code);
    		cart.setQuantity(cart.getQuantity() + 1);
    		cartDao.update(cart);
    	}
    	else {	// 장바구니에 없으면 새로 담음
    		Product product = productDao.selectByCode(code);
    		Cart cart = new Cart(code, product.getName(), product.getPrice(), 1);
    		cartDao.insert(cart);
    	}
    }

    // 결제
    public void pay() {
        for (int i = 0; i < cartDao.count(); i++) {
        	Cart cart = cartDao.selectAll().get(i);
        	
            // 재고 반영하기
            productDao.setQuantityByCode(cart.getCode(), productDao.getQuantityByCode(cart.getCode()) - cart.getQuantity());

            // 판매 정보 등록
            payDao.payInfo(cart.getName(), cart.getCode(), cart.getQuantity(), 
                productDao.getPriceByCode(cart.getCode()), cart.getPrice() * cart.getQuantity());
        }
    }
    
    // 선택한 장바구니 제품 삭제
    public void deleteProduct(String code) {
    	Cart cart = cartDao.selectByCode(code);
    	if (cart != null) cartDao.delete(cart);
    }
}
