package tech.csm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import tech.csm.model.Order;
import tech.csm.model.Product;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ProductDao productDao;
	
	@Override
	public List<Order> getAllOrder() {
		 SimpleJdbcCall simplejdbc= new SimpleJdbcCall(jdbcTemplate).withProcedureName("GetAllOrders");
		 
		 Map<String, Object> result = simplejdbc.execute();
	        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

	        List<Order> orders = new ArrayList<>();
	        for (Map<String, Object> row : rows) {
	            Order order = new Order();
	            // Assuming your Order class has setter methods for setting properties
	            order.setOrderId((Integer) row.get("orderId"));
	           order.setOrderDate((Date) row.get("orderDate"));
	           order.setOrderQty((String) row.get("orderQty"));
	           
	           Integer productId = (Integer) row.get("prodId");
	            if (productId != null) {
	                Product product = productDao.getProductById(productId);
	                order.setProduct(product);
	            }
	            orders.add(order);
	        }

	        return orders;
		
}

	public
	List<Order> getOrdersBetween(Date startDate, Date endDate){
		
		String sql="CALL GetOrderBetween(?,?)";
		List<Order> orderList= new ArrayList<>();
		
		 jdbcTemplate.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				 java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
				    java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

				    // Set the parameters in the PreparedStatement
				    ps.setDate(1, sqlStartDate);
				    ps.setDate(2, sqlEndDate);
				
			}
		} ,new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
		        order.setOrderId(rs.getInt("orderId"));
		        order.setOrderDate(rs.getDate("orderDate"));
		        order.setOrderQty(rs.getString("orderQty"));

		        // Map the product
		        Product product = new Product();
		        product.setProductId(rs.getInt("ProductId"));
		        product.setProdName(rs.getString("prodName"));
		        product.setProdRate(rs.getDouble("prodRate"));
		        product.setProdQty(rs.getString("prodQty"));

		        // Set the product in the order
		        order.setProduct(product);

		        orderList.add(order);
		        return order;
			}
		});
		return orderList;
//		return jdbcTemplate.execute(sql,new CallableStatementCallback<List<Order>>() {
//
//			@Override
//			public List<Order> doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
//				cs.setDate(1, new java.sql.Date (startDate.getTime()));
//				cs.setDate(2,  new java.sql.Date (endDate.getTime()));
//				
//
//				  ResultSet rs = cs.executeQuery();
//		            List<Order> orders = new ArrayList<>();
//		            while (rs.next()) {
//		                Order order = new Order();
//		                order.setOrderId(rs.getInt("orderId"));
//		                order.setOrderDate(rs.getDate("orderDate"));
//		                order.setOrderQty(rs.getString("orderQty"));
//
//		                // Assuming you have a Product class and a way to fetch it:
//		                int productId = rs.getInt("prodId");
//		                if (!rs.wasNull()) {
//		                    Product product = productDao.getProductById(productId);
//		                    order.setProduct(product);
//		                }
//
//		                orders.add(order);
//		             
//		            }
//		    
//		        }
//		});

	}
}