package tech.csm.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.Repo.ItemsRepo;
import tech.csm.Repo.salesMasterRepo;
import tech.csm.Repo.salesSlaveRepo;
import tech.csm.model.itemMaster;
import tech.csm.model.salesMaster;
import tech.csm.model.salesSlave;

@Service
public class BillingService {

	@Autowired
	ItemsRepo itemRepo;

	@Autowired
	salesMasterRepo salesMasterRepo;
	
	@Autowired
	salesSlaveRepo salesSlaveRepo;
	
	public void saveBill(String customerName,Integer itemId,Integer salesQuantity,Date dateofSales) {
		
		itemMaster item = itemRepo.findById(itemId).orElse(null);
		
		salesMaster salesmaster = new salesMaster();
		salesmaster.setCustomerName(customerName);
		salesmaster.setDateofSales(dateofSales);
		
		salesMasterRepo.save(salesmaster);
		
		 salesSlave salesSlave = new salesSlave();
	        salesSlave.setSalesMaster(salesmaster);
	        salesSlave.setItemMaster(item);
	        salesSlave.setSalesQty(salesQuantity);
	        
	        salesSlaveRepo.save(salesSlave);
		
	}
	   public Integer findAvailableQuantityById(Integer itemId) {
	        return itemRepo.findAvailableQuantityById(itemId);
	    }
	
	
}
