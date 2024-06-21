package tech.csm.dao;

import java.util.List;

import tech.csm.model.Sales;

public interface SalesDao {

	List<Sales> getAllSales();

	List<Sales> getSalesDetailsByGarageId(Integer garageId);

	Integer getQuantityAvailable(Integer tyreId);

	void saveSales(Integer salesId, String salesDate, Integer tyreId, Integer garageId, Integer salesQuantity);


}
