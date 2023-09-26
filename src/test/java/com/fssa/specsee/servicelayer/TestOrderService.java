package com.fssa.specsee.servicelayer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.specsee.enums.OrderStatus;
import com.fssa.specsee.exceptions.CustomException;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.modelobjects.Order;
import com.fssa.specsee.modelobjects.OrderProduct;
import com.fssa.specsee.modelobjects.Product;

public class TestOrderService {
		@Test
		public void testAddOrder() throws CustomException, SQLException, DAOException {

			
			Order order = new Order();
			List<OrderProduct> productsList = new ArrayList<>();
			int productId= 2;
			Product product=ProductService.getProductById(productId);
			OrderProduct product1 = new OrderProduct();
			product1.setProductId(productId);
			product1.setProductPrice(product.getProductPrice());
			product1.setQuantity(1);
			product1.setTotalAmount(product.getProductPrice());
			
			productsList.add(product1);

			order.setTotalAmount(186);
			order.setProductsList(productsList);
			order.setOrderDate(LocalDate.now());
			order.setUserID(1);
			order.setPhoneNumber("8940169934");
			order.setStatus(OrderStatus.ORDERED);
			System.out.println(order);
			Assertions.assertTrue(OrderService.addOrder(order));
		}

	}

