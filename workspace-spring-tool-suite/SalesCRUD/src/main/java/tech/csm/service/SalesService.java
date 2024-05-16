package tech.csm.service;

import java.util.List;

import tech.csm.model.Sales;

public interface SalesService {

	Sales saveSales(Sales sales);

	List<Sales> getAllSales();

	String deleteSalesById(Integer salesId);

	Sales getSalesById(Integer salesId);

}
