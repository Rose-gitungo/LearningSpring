package tech.csm.service;

import java.util.List;

import tech.csm.dto.CustomerSumDTO;
import tech.csm.dto.SalesSummaryDTO;
import tech.csm.model.salesSlave;

public interface SalesSlaveService {

	List<SalesSummaryDTO> findAll();
	
	

}
