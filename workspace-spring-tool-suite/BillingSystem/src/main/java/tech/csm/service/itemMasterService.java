package tech.csm.service;

import java.sql.Date;

public interface itemMasterService {

	void saveBill(String customerName,Integer itemId,Integer salesQuantity,Date dateofSales);
	
	Integer findAvailableQuantityById(Integer itemId);
}
