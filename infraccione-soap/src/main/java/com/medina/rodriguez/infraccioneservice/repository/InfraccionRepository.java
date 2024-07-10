package com.medina.rodriguez.infraccioneservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medina.rodriguez.infraccioneservice.entity.Infraccion;

public interface InfraccionRepository extends JpaRepository<Infraccion, Integer>{

}
