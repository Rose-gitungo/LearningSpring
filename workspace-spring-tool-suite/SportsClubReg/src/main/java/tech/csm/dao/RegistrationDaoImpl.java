package tech.csm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tech.csm.model.ClubMaster;
import tech.csm.model.Registration;
import tech.csm.model.SportsMaster;
@Repository
public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Registration> getAllUsers() {
		String sql="call GetRegistration";
		List<Registration> regList= jdbcTemplate.query(sql, new RowMapper<Registration>() {

			@Override
			public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
				Registration r=new Registration();
				r.setApplicantname(rs.getString(1));
				r.setEmail(rs.getString(2));
				r.setMobileno(rs.getString(3));
				r.setImagepath(rs.getString(4));
				
				ClubMaster c=new ClubMaster();
				c.setClubname(rs.getString(5));
				r.setClubMaster(c);
				
				SportsMaster s=new SportsMaster();
				s.setSportsname(rs.getString(6));
				s.setFees(rs.getDouble(7));
				r.setSportsMaster(s);
				return r;
			}});
		return regList;
	}

	@Override
	public Registration registerUser(Registration reg) {
		String sql="call UserSportsRegistartion(?)";
		jdbcTemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setObject(1, reg);
			}}, new RowMapper<Registration>() {

				@Override
				public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
				Registration r=new Registration();
				r.setRegistrationid(rs.getInt(1));
				r.setApplicantname(rs.getString(2));
				r.setEmail(rs.getString(3));
				r.setMobileno(rs.getString(4));
				//r.setDob(rs.getDate(5));
				r.setGender(rs.getString(5));
				r.setImagepath(rs.getString(6));
				
				
				return r;
				}
			} );
		return null;
	}

	@Override
	public Registration registerUser(Integer integer, String applicantname, String email, String mobileno,
			String gender, String imagepath, ClubMaster clubMaster, SportsMaster sportsMaster) {
		String sql="call UserSportsRegistartion(?,?,?,?,?,?,?,?)";
		 Registration registration = new Registration();
	 jdbcTemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				if (integer!=null) {
					ps.setInt(1, integer);
				}else {
					ps.setInt(1, 0);
				}
				if (applicantname!=null) {
					 ps.setString(2, applicantname);
				}else {
					 ps.setString(2, "null");
				}
		           
		            ps.setString(3, email);
		            if (mobileno!=null) {
		            	  ps.setString(4, mobileno);
					}else {
						  ps.setString(4, "null!");
					}
		            ps.setString(5, gender);
		          
		            if(imagepath!=null) {
		            	  ps.setString(6, imagepath);
		            }else {
		            	  ps.setString(6, "null");
		            }
		            if (clubMaster!=null) {
		            	 ps.setInt(7, clubMaster.getClubid());
					}else {
						ps.setInt(7, 1);
					}
		           if (sportsMaster!=null) {
		        	   ps.setInt(8, sportsMaster.getSportsid());
				}else {
					ps.setInt(8, 1);
				}
		            
				
			}}, new RowMapper<Registration>() {

				@Override
				public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
					
			            registration.setRegistrationid(rs.getInt(1));
			            registration.setApplicantname(rs.getString(2));
			            registration.setEmail(rs.getString(3));
			            registration.setMobileno(rs.getString(4));
			            registration.setGender(rs.getString(5));
			            registration.setImagepath(rs.getString(6));
			            ClubMaster c= new ClubMaster();
			            c.setClubid(rs.getInt(7));
			            registration.setClubMaster(c);
			            SportsMaster s= new SportsMaster();
			            s.setSportsid(rs.getInt(8));
			            registration.setSportsMaster(s);
			            return registration;
				}
			} );
		return registration;
	}

	@Override
	public List<Registration> getFilterByClubs(String string) {
		String sql="call GetALLByClubname(?)";
		List<Registration> rList= new ArrayList<Registration>();
		
		jdbcTemplate.query(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, string);
				
			}} , new RowMapper<Registration>() {

			@Override
			public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Registration r= new Registration();
				r.setApplicantname(rs.getString(1));
				r.setEmail(rs.getString(2));
				r.setMobileno(rs.getString(3));
				r.setImagepath(rs.getString(4));
				ClubMaster c=new ClubMaster();
				c.setClubname(rs.getString(5));
				SportsMaster s= new SportsMaster();
				s.setSportsname(rs.getString(6));
				rList.add(r);
				return r;
			}});
		
		return rList;
	}

}
