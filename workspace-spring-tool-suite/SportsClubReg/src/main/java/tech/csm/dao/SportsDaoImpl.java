package tech.csm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tech.csm.model.ClubMaster;
import tech.csm.model.SportsMaster;
@Repository
public class SportsDaoImpl implements SportsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<SportsMaster> getAllSports() {
		String query="call GetAllSport";
		List<SportsMaster> sportsList=jdbcTemplate.query(query, new RowMapper<SportsMaster>() {

			@Override
			public SportsMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
				SportsMaster  s=new SportsMaster();
				s.setSportsid(rs.getInt(1));
				s.setSportsname(rs.getString(2));
				
				ClubMaster c= new ClubMaster();
				c.setClubid(rs.getInt(3));
				c.setClubname(rs.getString(4));
				s.setClubMaster(c);
				
				s.setFees(rs.getDouble(5));
				return s;
			}});
		return sportsList;
	}
	
	@Override
	public List<SportsMaster> getSportsForClub(Integer clubid) {
		String sql=" call GetSportsForClub(?)";
		List<SportsMaster> sList= jdbcTemplate.query(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, clubid);
			}},new RowMapper<SportsMaster>() {

				@Override
				public SportsMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
				SportsMaster s=new SportsMaster();
				s.setSportsid(rs.getInt(1));
				s.setSportsname(rs.getString(2));
				ClubMaster c=new ClubMaster();
				c.setClubid(rs.getInt(3));
				s.setClubMaster(c);
				s.setFees(rs.getDouble(4));
					return s;
				}
			} );
		return sList;
	}

}
