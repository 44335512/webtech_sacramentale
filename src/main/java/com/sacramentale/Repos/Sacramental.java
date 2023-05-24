package com.sacramentale.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sacramentale.models.Models;

public interface Sacramental extends JpaRepository<Models, Integer> {

}
