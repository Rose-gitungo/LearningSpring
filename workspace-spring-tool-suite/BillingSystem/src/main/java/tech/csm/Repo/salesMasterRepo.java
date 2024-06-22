package tech.csm.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.model.salesMaster;

@Repository
public interface salesMasterRepo extends JpaRepository<salesMaster, Integer> {

	 @Query("SELECT s.salesMaster.customerName AS customerName, "
	            + "SUM(s.salesQty) AS totalItemsPurchased, "
	            + "SUM(s.salesQty * s.itemMaster.unitPrice) AS totalBilledAmount "
	            + "FROM salesSlave s "
	            + "GROUP BY s.salesMaster.customerName")
	    List<Object[]> fetchCustomerSummary();
	    
	    
	    @Query("SELECT s.salesMaster.customerName AS customerName, "
	            + "SUM(s.salesQty) AS totalItemsPurchased, "
	            + "SUM(s.salesQty * s.itemMaster.unitPrice) AS totalBilledAmount "
	            + "FROM salesSlave s "
	            + "WHERE DATE(s.salesMaster.dateofSales) = :selectedDate "
	            + "GROUP BY s.salesMaster.customerName")
	    List<Object[]> fetchCustomerSummaryByDate(@Param("selectedDate") LocalDate selectedDate);
}
