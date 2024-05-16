package tech.csm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import tech.csm.model.Garage;
import tech.csm.model.Sales;
import tech.csm.model.Tyre;
@Repository
public class SalesDaoImpl implements SalesDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Sales> getAllSales() {
		String sql="select S.sales_id,S.sales_date,S.sales_quantity,G.garage_id,G.garage_name,G.address,T.tyre_id,T.tyre_name"
				+ " from sales S "
				+ "inner join tyre T on S.tyre_id=T.tyre_id "
				+ " inner join garage G on S.garage_id= G.garage_id ";
		List<Sales> salesList= new ArrayList<>();
		
		jdbcTemplate.query(sql, new RowMapper<Sales>() {

			@Override
			public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sales s= new Sales();
				s.setSalesId(rs.getInt("sales_id"));
				s.setSalesDate(rs.getDate("sales_date"));
				
				Garage g= new Garage();
				g.setGarageId(rs.getInt("garage_id"));
				g.setGarageName(rs.getString("garage_name"));
				g.setAddress(rs.getString("address"));
				
				s.setGarage(g);
				
				Tyre t= new Tyre();
				t.setTyreId(rs.getInt("tyre_id"));
				t.setTyreName(rs.getString("tyre_name"));
				s.setTyre(t);
				s.setSalesQuantity(rs.getInt("sales_quantity"));
				salesList.add(s);
				return s;
			}});
		return salesList;
	}

	@Override
	public List<Sales> getSalesDetailsByGarageId(Integer garageId) {
		String sql="select S.sales_id,S.sales_date,S.sales_quantity,G.garage_id,G.garage_name,G.address,T.tyre_id,T.tyre_name"
				+ " from sales S "
				+ "inner join tyre T on S.tyre_id=T.tyre_id "
				+ " inner join garage G on S.garage_id= G.garage_id where G.garage_id =?";
		
		List<Sales> salesList= new ArrayList<>();
		
		jdbcTemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, garageId);
				
			}} , new RowMapper<Sales>() {

				@Override
				public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
					Sales s= new Sales();
					s.setSalesId(rs.getInt("sales_id"));
					s.setSalesDate(rs.getDate("sales_date"));
					
					Garage g= new Garage();
					g.setGarageId(rs.getInt("garage_id"));
					g.setGarageName(rs.getString("garage_name"));
					g.setAddress(rs.getString("address"));
					
					s.setGarage(g);
					
					Tyre t= new Tyre();
					t.setTyreId(rs.getInt("tyre_id"));
					t.setTyreName(rs.getString("tyre_name"));
					s.setTyre(t);
					s.setSalesQuantity(rs.getInt("sales_quantity"));
					salesList.add(s);
					return s;
				}
			});
		return salesList;
	}

	@Override
	public Integer getQuantityAvailable(Integer tyreId) {
		String sql="call gettingQty(?)";
		return 	jdbcTemplate.queryForObject(sql, Integer.class, tyreId);
		
	}

	@Override
	public Sales saveTyre(Sales sales) {
		/*
		 * String sql="call saveTyre(?,?,?,?,?)"; jdbcTemplate.query(sql, new
		 * PreparedStatementSetter() {
		 * 
		 * @Override public void setValues(PreparedStatement ps) throws SQLException {
		 * ps.setInt(1);
		 * 
		 * }}, null);
		 */
		return null;
	}

}
