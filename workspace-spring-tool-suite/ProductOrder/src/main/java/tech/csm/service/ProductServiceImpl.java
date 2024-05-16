package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.dao.ProductDao;
import tech.csm.model.Product;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
ProductDao productDao;
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllPRoducts();
	}

	@Override
	public Product getProductById(int i) {
		return productDao.getProductById(i);
	}

}
