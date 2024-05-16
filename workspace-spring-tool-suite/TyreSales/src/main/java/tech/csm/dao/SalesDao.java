package tech.csm.dao;

import java.util.List;

import tech.csm.model.Sales;

public interface SalesDao {

	List<Sales> getAllSales();

	List<Sales> getSalesDetailsByGarageId(Integer garageId);

	Integer getQuantityAvailable(Integer tyreId);

	Sales saveTyre(Sales sales);

}
