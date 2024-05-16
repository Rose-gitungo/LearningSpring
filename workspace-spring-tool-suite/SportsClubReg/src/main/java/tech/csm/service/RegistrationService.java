package tech.csm.service;

import java.util.List;

import tech.csm.model.ClubMaster;
import tech.csm.model.Registration;
import tech.csm.model.SportsMaster;

public interface RegistrationService {

	List<Registration> getAllUsers();

	Registration registerUser(Registration reg);

	Registration registerUser(Integer integer, String applicantname, String email, String mobileno, String gender,
			String imagepath, ClubMaster clubMaster, SportsMaster sportsMaster);

	List<Registration> getFilterByCLubs(String string);

}
