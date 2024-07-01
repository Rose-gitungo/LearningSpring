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
		return salesSlaveRepo.fetchSalesSlaveSummary();	
	} 
	
}
