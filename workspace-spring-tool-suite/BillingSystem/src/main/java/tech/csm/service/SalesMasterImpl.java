package tech.csm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.Repo.ItemsRepo;
import tech.csm.Repo.salesMasterRepo;
import tech.csm.Repo.salesSlaveRepo;
import tech.csm.dto.CustomerSumDTO;

@Service
public class SalesMasterImpl implements SalesMaster{
	
	@Autowired
	salesMasterRepo salesMasterRepo;
	
	 public List<CustomerSumDTO> getCustomerSummary() {
		 
			List<Object[]> customerSummary =  salesMasterRepo.fetchCustomerSummary();
			
			List<CustomerSumDTO> summaries= new ArrayList<>();
			for (Object[] row : customerSummary) {
			    String customerName = (String) row[0];  
			    Integer totalItemsPurchased = ((Long) row[1]).intValue();  
			    Double totalBilledAmount = (Double) row[2];  
			    
			 CustomerSumDTO cs = new CustomerSumDTO(customerName,totalItemsPurchased,totalBilledAmount);
			 summaries.add(cs);	    
	    }
			return summaries;
}

	@Override
	public List<CustomerSumDTO> getCustomerSummary(LocalDate selectedDate) {
		List<Object[]> customerSummary =  salesMasterRepo.fetchCustomerSummaryByDate(selectedDate);
		
		List<CustomerSumDTO> summaries= new ArrayList<>();
		for (Object[] row : customerSummary) {
		    String customerName = (String) row[0];  
		    Integer totalItemsPurchased = ((Long) row[1]).intValue();  
		    Double totalBilledAmount = (Double) row[2];  
		    
		 CustomerSumDTO cs = new CustomerSumDTO(customerName,totalItemsPurchased,totalBilledAmount);
		 summaries.add(cs);	  
		
	}
		return summaries;
	}
}
