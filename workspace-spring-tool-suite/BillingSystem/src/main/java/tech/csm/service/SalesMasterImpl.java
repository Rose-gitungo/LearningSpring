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
	

	@Override
	public List<CustomerSumDTO> getCustomerSummary(LocalDate selectedDate) {
//		return salesMasterRepo.fetchCustomerSummaryByDate(selectedDate);
		return null;
	}


	@Override
	public List<CustomerSumDTO> getCustomerSummary() {
		List<CustomerSumDTO> customlist = salesMasterRepo.fetchCustomerSummary();
		System.out.println(customlist);
		return customlist;
	}
}
