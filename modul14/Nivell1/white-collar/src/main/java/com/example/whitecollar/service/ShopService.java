package com.example.whitecollar.service;

import java.util.List;
import java.util.Optional;

import com.example.whitecollar.entity.Shop;

public interface ShopService {

	public List<Shop> getAllShops();

	public Optional<Shop> getShopById(int id);

	public Shop addShop(Shop shop);
	
	public void updateShop(Shop shop);

}
