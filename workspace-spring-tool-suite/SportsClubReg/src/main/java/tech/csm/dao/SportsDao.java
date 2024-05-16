package tech.csm.dao;

import java.util.List;

import tech.csm.model.SportsMaster;

public interface SportsDao {

	List<SportsMaster> getAllSports();

	List<SportsMaster> getSportsForClub(Integer clubid);

}
