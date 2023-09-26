package com.fssa.specsee.servicelayer;

import java.sql.SQLException;

import com.fssa.specsee.daolayer.CartDAO;
import com.fssa.specsee.exceptions.CustomException;
import com.fssa.specsee.exceptions.DAOException;
import com.fssa.specsee.modelobjects.Cart;

public class CartService {

	public static boolean addToCart(Cart cart) throws DAOException, CustomException, SQLException {

		if (true) {
			// validation needed

			CartDAO.addToCart(cart);
			return true; 
		}
		return false;

	}
}