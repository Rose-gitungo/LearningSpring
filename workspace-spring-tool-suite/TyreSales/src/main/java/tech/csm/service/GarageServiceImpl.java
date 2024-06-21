package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.dao.GarageDao;
import tech.csm.model.Garage;
@Service
public class GarageServiceImpl implements GarageService {

	@Autowired
	GarageDao garageDao;

	@Override
	public List<Garage> getAllGarages() {
		return garageDao.getAllGarages();
	}

}
