package tech.csm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tech.csm.model.Garage;
@Repository
public class GarageDaoImpl implements GarageDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Garage> getAllGarages() {
		List<Garage> garageList= new ArrayList<>();
		String sql="select * from garage";
		
		jdbcTemplate.query(sql,new RowMapper<Garage>() {

			@Override
			public Garage mapRow(ResultSet rs, int rowNum) throws SQLException {
			Garage g=new Garage();
			g.setGarageId(rs.getInt(1));
			g.setGarageName(rs.getString(2));
			g.setAddress(rs.getString(3));
			garageList.add(g);
				return g;
			}});
		return garageList;
	}

}
