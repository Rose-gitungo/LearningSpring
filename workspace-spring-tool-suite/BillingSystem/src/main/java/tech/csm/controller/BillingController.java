package tech.csm.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.csm.Repo.ItemsRepo;
import tech.csm.Repo.salesSlaveRepo;
import tech.csm.dto.SalesSummaryDTO;
import tech.csm.model.itemMaster;
import tech.csm.model.salesSlave;
import tech.csm.service.SalesSlaveService;
import tech.csm.service.itemMasterService;
import tech.csm.service.SalesMasterImpl;

@Controller
public class BillingController {
	
	@Autowired
	ItemsRepo itemsRepo;
	
	@Autowired
	SalesSlaveService salesService;
	
	@Autowired
	itemMasterService itemService;
	
	@GetMapping("/getBillingPage")
	public String Billing(Model model) {
		List<itemMaster> itemList = itemsRepo.findAll();
		List<SalesSummaryDTO> customeritemslist = salesService.findAll();
		
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("dateofSales",currentDate);
		model.addAttribute("itemList", itemList);
		model.addAttribute("customeritemslist", customeritemslist);
		return "billingPage";
	}
		
	@PostMapping("/saveBill")
	public String saveBill(
			@RequestParam("customerName") String customerName,
            @RequestParam("itemId") Integer itemId,
            @RequestParam("salesQuantity") Integer salesQuantity,
           @RequestParam("dateofSales")  Date dateofSales) {
		
		itemService.saveBill(customerName, itemId, salesQuantity,dateofSales);
		
		return "redirect:/getBillingPage";
	}
	@GetMapping("getAvailableQuantity")
	public Integer getAvailableQuantity(@RequestParam Integer itemId) {
		return itemService.findAvailableQuantityById(itemId);
		
	}

}
