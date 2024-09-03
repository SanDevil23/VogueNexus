package com.sankalp.shoppingServer.repository;

import com.sankalp.shoppingServer.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
