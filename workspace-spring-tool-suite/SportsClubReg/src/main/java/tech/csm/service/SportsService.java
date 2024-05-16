package tech.csm.service;

import java.util.List;

import tech.csm.model.SportsMaster;

public interface SportsService {

	List<SportsMaster> getAllSports();

	List<SportsMaster> getSportsForCLub(Integer clubid);

}
