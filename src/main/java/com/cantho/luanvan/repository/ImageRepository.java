package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsById(Long id);

}
