package tech.csm.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import tech.csm.dao.GarageDao;
import tech.csm.dao.SalesDao;
import tech.csm.dao.TyreDao;

import tech.csm.model.Garage;
import tech.csm.model.Sales;
import tech.csm.model.Tyre;


@Controller
public class TyreController {
	
	@Autowired
	GarageDao garageDao;
	@Autowired
	SalesDao salesDao;
	@Autowired
	TyreDao tyreDao;
	
@GetMapping("/getform")
	public String getform(Model model) {
	List<Garage> garageList= garageDao.getAllGarages();
	List<Sales> salesList= salesDao.getAllSales();
	List<Tyre> tyreList= tyreDao.getAllTyres();
	
	model.addAttribute("garageList", garageList);
	model.addAttribute("tyreList", tyreList);
	model.addAttribute("salesList", salesList);
	model.addAttribute("salesDate", java.sql.Date.valueOf(LocalDate.now()));

		return "registration";
	}

@GetMapping("/getSalesDetails")
public void getSalesDetails(@RequestParam("garageId") Integer garageId ,HttpServletResponse response) throws IOException {
	List<Sales> salesList=salesDao.getSalesDetailsByGarageId(garageId);
	
	 // Building the table HTML
    StringBuilder tableHtml = new StringBuilder();
    int counter = 1; 
    for (Sales sales : salesList) {
        tableHtml.append("<tr>")
        .append("<td>").append(counter++).append("</td>")
                .append("<td>").append(sales.getTyre().getTyreName()).append("</td>")
                .append("<td>").append(sales.getSalesQuantity()).append("</td>")
                .append("</tr>");
    }

    tableHtml.append("</tbody>");

    response.getWriter().print(tableHtml.toString());
}
@GetMapping("/getQuantityAvailable")
public void getQuantityAvailable(@RequestParam("tyreId") Integer tyreId ,HttpServletResponse resp) throws IOException {
	Integer rem= salesDao.getQuantityAvailable(tyreId);
	resp.getWriter().print(rem);
}

@PostMapping("/saveTyre")
@ResponseBody
public ResponseEntity<String> saveTyre( 
		@RequestParam("salesId") Integer salesId,
        @RequestParam("salesDate") String salesDate,
        @RequestParam("garage") Integer garageId,
        @RequestParam("tyre") Integer tyreId,
        @RequestParam("salesQuantity") Integer salesQuantity) {
     
     salesDao.saveSales(salesId, salesDate, tyreId, garageId,salesQuantity);
	return ResponseEntity.ok("Sales saved successfully");
}
}
