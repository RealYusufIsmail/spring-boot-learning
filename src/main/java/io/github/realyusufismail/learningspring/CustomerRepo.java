package io.github.realyusufismail.learningspring;

import org.springframework.data.jpa.repository.JpaRepository;

//Inject to be able to query the database

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}