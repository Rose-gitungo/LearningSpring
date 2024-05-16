package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.dao.RegistrationDao;
import tech.csm.model.ClubMaster;
import tech.csm.model.Registration;
import tech.csm.model.SportsMaster;
@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationDao regDao;
	
	@Override
	public List<Registration> getAllUsers() {
		return regDao.getAllUsers();
	}

	@Override
	public Registration registerUser(Registration reg) {
		return regDao.registerUser(reg);
	}

	@Override
	public Registration registerUser(Integer integer, String applicantname, String email, String mobileno,
			String gender, String imagepath, ClubMaster clubMaster, SportsMaster sportsMaster) {
		// TODO Auto-generated method stub
		return regDao.registerUser(integer,applicantname,email,mobileno,gender,imagepath,clubMaster,sportsMaster);
	}


	@Override
	public List<Registration> getFilterByCLubs(String string) {
		return regDao.getFilterByClubs(string);
	}

}
