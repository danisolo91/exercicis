package com.example.whitecollar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.whitecollar.entity.Shop;
import com.example.whitecollar.repository.ShopRepository;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public List<Shop> getAllShops() {
		return shopRepository.findAll();
	}

	@Override
	public Optional<Shop> getShopById(int id) {
		return shopRepository.findById(id);
	}

	@Override
	public Shop addShop(Shop shop) {
		return shopRepository.save(shop);
	}

	@Override
	public void updateShop(Shop shop) {
		shopRepository.save(shop);
	}

}
