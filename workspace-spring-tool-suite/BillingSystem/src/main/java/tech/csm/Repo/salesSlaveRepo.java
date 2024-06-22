package tech.csm.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.model.salesSlave;
@Repository
public interface salesSlaveRepo extends JpaRepository<salesSlave, Integer> {
	    
	    @Query("SELECT i.itemName, ss.salesQty, i.unitPrice, (ss.salesQty * i.unitPrice) " +
	    	       "FROM salesMaster sm " +
	    	       "JOIN salesSlave ss ON sm.salesId = ss.salesMaster.salesId " +
	    	       "JOIN itemMaster i ON ss.itemMaster.itemId = i.itemId")
	    List<Object[] > fetchSalesSlaveSummary();

	    
}
