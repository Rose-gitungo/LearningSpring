package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.dao.ClubDao;
import tech.csm.model.ClubMaster;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	ClubDao clubDao;
	
	@Override
	public List<ClubMaster> getAllClubs() {
		return clubDao.getAllClubs();
	}

}
