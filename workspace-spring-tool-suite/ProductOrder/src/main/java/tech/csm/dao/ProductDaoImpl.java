package tech.csm.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import tech.csm.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Product> getAllPRoducts() {
		SimpleJdbcCall simplejdbc = new SimpleJdbcCall(jdbcTemplate).withProcedureName("GetAllProducts");

		Map<String, Object> result = simplejdbc.execute();
		List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

		List<Product> orders = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			Product p = new Product();

			p.setProductId((Integer) row.get("productId"));
			p.setProdName((String) row.get("ProdName"));
			p.setProdRate((Double) row.get("prodRate"));
			p.setProdQty((String) row.get("prodQty"));
			orders.add(p);
		}
		return orders;
	}

	@Override
	public Product getProductById(Integer productId) {
		String query="call GetProductById(?)";
		 return jdbcTemplate.execute(query, new CallableStatementCallback<>() {

			@Override
			public Product doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setInt(1, productId);
				ResultSet rs=cs.executeQuery();
				Product p=new Product();
				if (rs.next()) {
					p.setProductId(productId);
					p.setProdName(rs.getString(2));
					p.setProdRate(rs.getDouble(3));
					p.setProdQty(rs.getString(4));
				}
				return p;
			}
		});
	}

}
