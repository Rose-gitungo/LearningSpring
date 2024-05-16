package tech.csm.dao;

import java.util.List;

import tech.csm.model.ClubMaster;
import tech.csm.model.Registration;
import tech.csm.model.SportsMaster;

public interface RegistrationDao {

	List<Registration> getAllUsers();

	Registration registerUser(Registration reg);

	Registration registerUser(Integer integer, String applicantname, String email, String mobileno, String gender,
			String imagepath, ClubMaster clubMaster, SportsMaster sportsMaster);

	List<Registration> getFilterByClubs(String string);

}
