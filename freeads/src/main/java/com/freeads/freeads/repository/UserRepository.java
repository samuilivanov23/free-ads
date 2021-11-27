package com.freeads.freeads.repository;

import com.freeads.freeads.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> 
{

}
