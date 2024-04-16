package com.example.Motoshop.repository;

import com.example.Motoshop.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository <Moto,Long> {
    List<Moto> findByTitle(String title);
}
