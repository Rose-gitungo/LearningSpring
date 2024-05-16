package tech.csm.service;

import java.util.Date;
import java.util.List;

import tech.csm.model.Order;

public interface OrderService {

	List<tech.csm.model.Order> getAllOrders();

	List<Order> getOrdersBetween(Date startDate, Date endDate);

}
