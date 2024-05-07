package com.KeyCloakTest.KeyCloakTest.Repository;

import com.KeyCloakTest.KeyCloakTest.Models.Userdet;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Userdet, Long> {
    // Spring Data JDBC enables simple CRUD operations without additional code
}
