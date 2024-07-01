package tech.csm.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.dto.CustomerSumDTO;
import tech.csm.dto.SalesSummaryDTO;
import tech.csm.model.salesSlave;
@Repository
public interface salesSlaveRepo extends JpaRepository<salesSlave, Integer> {
	    
	    @Query("SELECT new tech.csm.dto.SalesSummaryDTO(i.itemName as itemName, ss.salesQty as salesQty, i.unitPrice as unitPrice, (ss.salesQty * i.unitPrice) as totalAmount) " +
	    	       "FROM salesMaster sm " +
	    	       "JOIN salesSlave ss ON sm.salesId = ss.salesMaster.salesId " +
	    	       "JOIN itemMaster i ON ss.itemMaster.itemId = i.itemId")
	    List<SalesSummaryDTO> fetchSalesSlaveSummary();
	    
}
