package tech.csm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.Repo.salesSlaveRepo;
import tech.csm.dto.CustomerSumDTO;
import tech.csm.dto.SalesSummaryDTO;
import tech.csm.model.salesSlave;

@Service
public class SalesSlaveServiceImpl implements SalesSlaveService {
	
	@Autowired
	salesSlaveRepo salesSlaveRepo;

	@Override
	public List<SalesSummaryDTO> findAll() {
		List<Object[] > salesslaveList = salesSlaveRepo.fetchSalesSlaveSummary();	
        List<SalesSummaryDTO> summaries= new ArrayList<>();

		
		for (Object[] row : salesslaveList) {
			 String itemName = (String) row[0];
	            Integer salesQty = (Integer) row[1];
	            Double unitPrice = (Double) row[2];
	            Double totalAmount = (Double) row[3];
		    
	            SalesSummaryDTO dto = new SalesSummaryDTO(itemName, salesQty, unitPrice, totalAmount);
	            summaries.add(dto);  
		}
		return summaries;
	} 
	
	

}
