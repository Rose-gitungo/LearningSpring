package tech.csm.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tech.csm.dto.CustomerSumDTO;
import tech.csm.service.SalesMaster;
import tech.csm.service.SalesMasterImpl;

@Controller
public class SummaryController {

	@Autowired
	SalesMaster salesMaster;
	
	@GetMapping("/getSummaryPage")
	public String Summary(Model model) {
		List<CustomerSumDTO> summaries= salesMaster.getCustomerSummary();
		 model.addAttribute("summaries", summaries);
		 System.out.println(summaries);
		return "summaryPage";
	}
	
	 @GetMapping("/getCustomerSummary")
	    public String getCustomerSummary(@RequestParam("date") String dateString, Model model) {
	        LocalDate selectedDate = LocalDate.parse(dateString);
	        List<CustomerSumDTO> summaries = salesMaster.getCustomerSummary(selectedDate);
	        model.addAttribute("summaries", summaries);
	        return "summaryPage";
	    }
	
}
