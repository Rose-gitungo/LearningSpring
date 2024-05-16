package tech.csm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.dao.OrderDao;
import tech.csm.model.Order;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDao orderDao;
	
	@Override
	public List<tech.csm.model.Order> getAllOrders() {
		return orderDao.getAllOrder();
	}


	@Override
	public List<Order> getOrdersBetween(Date startDate, Date endDate) {
		return orderDao.getOrdersBetween(startDate,endDate);
	}

}
