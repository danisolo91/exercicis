package com.example.whitecollar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.whitecollar.entity.Picture;
import com.example.whitecollar.entity.Shop;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

	@Transactional
	public long deleteByShop(Shop shop);

}