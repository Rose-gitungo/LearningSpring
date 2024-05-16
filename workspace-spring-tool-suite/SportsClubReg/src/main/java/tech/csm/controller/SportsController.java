package tech.csm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import tech.csm.model.ClubMaster;
import tech.csm.model.Registration;
import tech.csm.model.SportsMaster;
import tech.csm.service.ClubService;
import tech.csm.service.RegistrationService;
import tech.csm.service.SportsService;

@Controller
public class SportsController {

	@Autowired
	ClubService clubService;
	
	@Autowired
	SportsService sportsService;
	@Autowired
	RegistrationService registrationService;
	
	@GetMapping("/getform")
	public String getform(Model model) {
	List<ClubMaster>clubList= clubService.getAllClubs();
	List<SportsMaster>sportsList= sportsService.getAllSports();
	List<Registration> regList= registrationService.getAllUsers();
		
	model.addAttribute("sportsList",sportsList);
	model.addAttribute("clubList", clubList);
	model.addAttribute("regList", regList);
		return "registration";
	}
	
	@GetMapping("/getSportsForClub")
	public void getSportsForClub(@RequestParam("clubid")Integer clubid,HttpServletResponse resp) throws IOException {
		//fetch sports based on club id
		List<SportsMaster> sp= sportsService.getSportsForCLub(clubid);
		// Convert sports list to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonSportsList = objectMapper.writeValueAsString(sp);
        // Set response content type to JSON
        resp.setContentType("application/json");
        // Write JSON to response
        resp.getWriter().write(jsonSportsList);
	}
	
	@PostMapping("/saveSports")
	public String saveSports(@ModelAttribute Registration reg) throws ParseException {
	 Registration r=registrationService.registerUser(reg.getRegistrationid(), reg.getApplicantname(), reg.getEmail(), reg.getMobileno(), reg.getGender(), reg.getImagepath(), reg.getClubMaster(), reg.getSportsMaster());
	 System.out.println(r);
		return "redirect:./getform";
	}
	
	@PostMapping("/filter")
	public String filterClubs(@RequestParam("clubName")String clubname,Model model) {
		List<Registration> rList= registrationService.getFilterByCLubs(clubname);
		
		List<ClubMaster>clubList= clubService.getAllClubs();
		List<SportsMaster>sportsList= sportsService.getAllSports();
		List<Registration> regList= registrationService.getAllUsers();
			
		model.addAttribute("sportsList",sportsList);
		model.addAttribute("clubList", clubList);
		model.addAttribute("regList", rList);
	
		return "registration";
	}
}
