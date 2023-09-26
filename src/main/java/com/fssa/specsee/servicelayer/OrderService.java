package com.fssa.specsee.servicelayer;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.specsee.daolayer.OrderDAO;
import com.fssa.specsee.exceptions.CustomException;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.modelobjects.Order;
import com.fssa.specsee.validator.OrderValidator;

	public class OrderService {
		public static boolean addOrder(Order order) throws DAOException, CustomException, SQLException {

			if (OrderValidator.validateOrder(order)) {

				OrderDAO.addOrder(order);
				return true;
			}
			return false; 
			
		}
		public static ArrayList<Order> getOrderById(int orderId) throws DAOException, CustomException, SQLException {

			OrderDAO orderDao = new OrderDAO();
			return orderDao.getOrderById(orderId);

		}
	
		public static void deleteOrderedProductsByOrderId(int orderId) throws DAOException, CustomException, SQLException {
			OrderDAO.deleteOrderedProductsByOrderId(orderId);
		}

		public static boolean cancelOrder(int orderId) throws DAOException, CustomException, SQLException {
			
		return OrderDAO.cancelOrder(orderId);

		}
	
	}
