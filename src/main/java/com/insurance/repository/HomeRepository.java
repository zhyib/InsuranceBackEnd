package com.insurance.repository;

import com.insurance.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<Home, Integer> {
}
