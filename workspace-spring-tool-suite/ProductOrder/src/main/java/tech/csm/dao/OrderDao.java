package tech.csm.dao;

import java.util.Date;
import java.util.List;

import tech.csm.model.Order;

public interface OrderDao {

	List<tech.csm.model.Order> getAllOrder();

	List<Order> getOrdersBetween(Date startDate, Date startDate2);

}
