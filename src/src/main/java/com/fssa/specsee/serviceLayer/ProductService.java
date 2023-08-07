package com.fssa.specsee.serviceLayer;

import java.sql.SQLException;

import com.fssa.specsee.DAOLayer.ProductDAO;
import com.fssa.specsee.modelObjects.Product;
import com.fssa.specsee.validator.ProductValidator;

public class ProductService {
	
	
	

		public static boolean addProduct(Product product) throws IllegalArgumentException {
			if (ProductValidator.validate(product)) {
			ProductDAO.addProduct(product);
			}
			return true;
		}

		public static boolean readProduct() throws IllegalArgumentException, SQLException {
			ProductDAO.readProduct();
			return true;

		}

		public static boolean updateProduct(Product product, int id) throws IllegalArgumentException {
			if (ProductValidator.validate(product)) {
				ProductDAO.updateProduct(product, id);
			}
			return true;

		}

		public static boolean deleteProduct(int id) throws IllegalArgumentException{
			
			ProductDAO.deleteProduct(id);
			return true;

		}

		public static boolean findProductByName(String name) throws IllegalArgumentException, SQLException {
			if (ProductValidator.productNameValidator(name)) {
				ProductDAO.findProductByName(name);
			}
			return true;

		}
	
}
