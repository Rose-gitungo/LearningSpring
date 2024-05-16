package tech.csm.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tech.csm.model.user;
import tech.csm.repo.UserRepo;
import tech.csm.service.UserService;

@Controller
public class userController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserService userService;

	@GetMapping("/getform")
	public String getForm(Model model) {
		List<user> userList = userRepo.findAll();

		model.addAttribute("userList", userList);
		return "registration";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute user user, @RequestParam("image") MultipartFile file, RedirectAttributes rd)
			throws IOException {
		String filename = file.getOriginalFilename();
		user.setFileName(filename); // setting file path

		// is file stored anywhere?
		InputStream inputStream = file.getInputStream();
		byte[] data = inputStream.readAllBytes();
		new FileOutputStream("C:/Main/" + filename).write(data);

		// check is user with same id exists
		if (userService.existsByUserId(user.getUserId())) {
			rd.addFlashAttribute("USerid has to be unique");
		}

		userService.saveUser(user);
		return "redirect:./getform";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("uId") String userId) {
		userService.deleteUserById(userId);
		return "redirect:./getform";
	}

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("uId") String userId, Model model) {
		user u = userService.updateUserById(userId);
	
		  List<user> userList = userRepo.findAll();
		  
		  model.addAttribute("userList", userList);
		 
	model.addAttribute("u", u);
		System.out.println(u);
		userRepo.save(u);
		return "registration";
	}
}
