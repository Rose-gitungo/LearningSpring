package tech.csm.dao;

import java.util.List;

import tech.csm.model.Product;

public interface ProductDao {

	List<Product> getAllPRoducts();

	Product getProductById(Integer productId);

}
