package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.Product;
import tech.csm.model.Sales;
import tech.csm.repo.ProductRepo;
import tech.csm.repo.SalesRepo;

@Service
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SalesRepo salesRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public Sales saveSales(Sales sales) {
		Sales s=salesRepo.save(sales);
		Product p=s.getProduct();
		p.setQuantity(p.getQuantity()-s.getSalesQnty()); //reducing quantity
		productRepo.save(p);
		return salesRepo.save(sales);
	}

	@Override
	public List<Sales> getAllSales() {
		return salesRepo.findAll();
	}

	@Override
	public String deleteSalesById(Integer salesId) {
		//get it
		Sales s=salesRepo.findById(salesId).get();
		Product p=s.getProduct();
		p.setQuantity(p.getQuantity()+s.getSalesQnty()); //adding quantity back
		salesRepo.delete(s);
		return null;
	}

	@Override
	public Sales getSalesById(Integer salesId) {
		return salesRepo.findById(salesId).get();
	}

}
