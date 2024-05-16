package tech.csm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tech.csm.model.ClubMaster;

@Repository
public class ClubDaoImp implements ClubDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public ClubDaoImp() {
		
	}

	@Override
	public List<ClubMaster> getAllClubs() {
			String sql ="call GetAllClubs";
			List<ClubMaster> clubList= jdbcTemplate.query(sql,new RowMapper<ClubMaster>() {

				@Override
				public ClubMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
					ClubMaster c=new ClubMaster();
					c.setClubid(rs.getInt(1));
					c.setClubname(rs.getString(2));
					return c;
				}} );
	
		return clubList;
	}

}
