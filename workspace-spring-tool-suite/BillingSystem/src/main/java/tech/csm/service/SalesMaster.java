package tech.csm.service;

import java.time.LocalDate;
import java.util.List;

import tech.csm.dto.CustomerSumDTO;

public interface SalesMaster {

	List<CustomerSumDTO> getCustomerSummary();

	List<CustomerSumDTO> getCustomerSummary(LocalDate selectedDate);
}
