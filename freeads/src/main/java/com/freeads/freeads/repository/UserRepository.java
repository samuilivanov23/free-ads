package com.freeads.freeads.repository;

import com.freeads.freeads.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> 
{
	@Modifying
	@Query( 
		value="UPDATE users SET first_name=:firstName, last_name=:lastName, username=:username, email_address=:emailAddress where id=:id",
		nativeQuery=true	
	)
	void updateAdminUserData( @Param( value="id" ) long id,
						 @Param( value="firstName" ) String firstName,
						 @Param( value="lastName" ) String lastName,
						 @Param( value="username" ) String username,
						 @Param( value="emailAddress" ) String emailAddress );

	@Query(
		value="SELECT * FROM users as u WHERE u.id=:id",
		nativeQuery=true
	)
	User findAdminById( @Param( value="id" ) long id );

	@Modifying
	@Query(
		value="DELETE FROM users WHERE id=:id",
		nativeQuery=true
	)
	void deleteAdminUser( @Param( value="id" ) long id );
}
