package tech.csm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tech.csm.model.user;

@Repository
public interface UserRepo extends JpaRepository<user,String > {

}