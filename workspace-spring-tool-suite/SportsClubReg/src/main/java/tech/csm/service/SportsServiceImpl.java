package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.dao.SportsDao;
import tech.csm.model.SportsMaster;

@Service
public class SportsServiceImpl implements SportsService {

	@Autowired
	SportsDao sportsDao;
	
	@Override
	public List<SportsMaster> getAllSports() {
		return sportsDao.getAllSports();
	}


	@Override
	public List<SportsMaster> getSportsForCLub(Integer clubid) {
		// TODO Auto-generated method stub
		return sportsDao.getSportsForClub(clubid);
	}

}
