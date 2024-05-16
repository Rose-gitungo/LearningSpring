package tech.csm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tech.csm.model.Tyre;

@Repository
public class TyreDaoImpl implements TyreDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Tyre> getAllTyres() {
		String sql ="select * from tyre";
		List<Tyre> tyreList= new ArrayList<>();
		
		jdbcTemplate.query(sql,new RowMapper<Tyre>() {

			@Override
			public Tyre mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tyre t= new Tyre();
				t.setTyreId(rs.getInt(1));
				t.setTyreName(rs.getString(2));
				t.setQuantity(rs.getInt(3));
				t.setUnitPrice(rs.getDouble(4));
				tyreList.add(t);
				return t;
			}});
		
		return tyreList;
	}

}
