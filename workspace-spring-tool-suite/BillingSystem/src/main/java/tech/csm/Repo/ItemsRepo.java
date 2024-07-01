package tech.csm.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.csm.model.itemMaster;

@Repository
public interface ItemsRepo extends JpaRepository<itemMaster, Integer>{
	
	 @Query("SELECT i.Quantity FROM itemMaster i WHERE i.itemId = :itemId")
	    Integer findAvailableQuantityById(Integer itemId); 
	 
}
