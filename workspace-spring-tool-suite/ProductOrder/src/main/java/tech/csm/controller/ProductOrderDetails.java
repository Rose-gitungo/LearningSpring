package tech.csm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import tech.csm.dao.ProductDao;
import tech.csm.model.Order;
import tech.csm.model.Product;
import tech.csm.service.OrderService;
import tech.csm.service.ProductService;

@Controller
public class ProductOrderDetails {

	@Autowired
	OrderService orderService;
	@Autowired
	ProductService productService;

	@Autowired
	ProductDao productDao;
	
	@GetMapping("/getform")
	public String getform(Model model) {
		List<tech.csm.model.Order> orderList = orderService.getAllOrders();

		model.addAttribute("orderList", orderList);
		return "registration";
	}

	@GetMapping("/saveOrderDtls")
	@ResponseBody
	public List<Order> saveDetails(@RequestParam("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate ,@RequestParam("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate, HttpServletResponse resp) throws IOException {
		//save the dates and use it to filter 
		List<Order> filteredOrderDetails= orderService.getOrdersBetween(startDate,endDate);
		System.out.println(filteredOrderDetails);
		resp.getWriter().print(filteredOrderDetails);
	return filteredOrderDetails;
	}
	
	}


