package tech.csm.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.dto.CustomerSumDTO;
import tech.csm.model.salesMaster;

@Repository
public interface salesMasterRepo extends JpaRepository<salesMaster, Integer> {

	 @Query("SELECT new tech.csm.dto.CustomerSumDTO(sm.customerName, SUM(ss.salesQty), SUM(ss.salesQty * im.unitPrice)) " +
	           "FROM salesMaster sm " +
	           "JOIN sm.salesSlaves ss " +
	           "JOIN ss.itemMaster im " +
	           "GROUP BY sm.customerName")
	 List<CustomerSumDTO> fetchCustomerSummary();
	    
	    
//	    @Query("SELECT  tech.csm.dto.CustomerSumDTO(s.salesMaster.customerName AS customerName, "
//	            + "SUM(s.salesQty) AS totalItemsPurchased, "
//	            + "SUM(s.salesQty * s.itemMaster.unitPrice) AS totalBilledAmount) "
//	            + "FROM salesSlave s "
//	            + "WHERE DATE(s.salesMaster.dateofSales) = :selectedDate "
//	            + "GROUP BY s.salesMaster.customerName")
	@Query("from salesMaster")
	    List<CustomerSumDTO> fetchCustomerSummaryByDate(@Param("selectedDate") LocalDate selectedDate);
}
