package tech.csm.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import tech.csm.model.Customer;
import tech.csm.model.Product;
import tech.csm.model.Sales;
import tech.csm.service.CustomerService;
import tech.csm.service.ProductService;
import tech.csm.service.SalesService;

@Controller
public class SalesController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SalesService salesService;

	@GetMapping("/getsales")
	public String getSalesForm(Model model) {
		
	List<Product> productList=productService.getAllProducts();
	 List<Customer> customerList=customerService.getALlCustomers();
	 List<Sales> salesList= salesService.getAllSales();
	 
	 model.addAttribute("productList", productList);
	 model.addAttribute("customerList", customerList);
	 model.addAttribute("salesList", salesList);
		return "salesform";
	}
	
	@PostMapping("/saveSales")
	public String saveSales(@ModelAttribute Sales  sales ,RedirectAttributes rd) {
		//form data here!
		sales.setSalesDate(new Date());
		Sales s=salesService.saveSales(sales);
		rd.addFlashAttribute("msg","Sale has been saved successfully with Id: "+s.getSalesId());
		return "redirect:./getsales";
	}
	//trying to implement with javascript how to get product quantity
	@GetMapping("/getProductQntyByProductId")
	public void getProductByIs(@RequestParam("productId")Integer productId , HttpServletResponse resp) throws IOException {
		Product p= productService.getProductQntyByProductId(productId);
		resp.getWriter().print(p.getQuantity());
	}
	
	
	@GetMapping("/delSales")
	public String delSalesById(@RequestParam("sId") Integer salesId ,RedirectAttributes rd) {
		salesService.deleteSalesById(salesId);
		rd.addFlashAttribute("msg","Sale deleted successfully!");
		return "redirect:./getsales";
	}
	
	@GetMapping("/updateSales")
	public String updateSalesById(Model model, @RequestParam("sId") Integer salesId ,RedirectAttributes rd) {
		Sales s=salesService.getSalesById(salesId);
		
		List<Product> productList=productService.getAllProducts();
		 List<Customer> customerList=customerService.getALlCustomers();
		 List<Sales> salesList= salesService.getAllSales();
		 
		 model.addAttribute("usales", s);
		 model.addAttribute("productList", productList);
		 model.addAttribute("customerList", customerList);
		 model.addAttribute("salesList", salesList);
		 
		 rd.addFlashAttribute("msg","Sale updated successfully!");
		return "salesform";
	}
}
