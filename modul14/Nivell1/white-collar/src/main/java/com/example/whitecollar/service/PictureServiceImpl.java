package com.example.whitecollar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.whitecollar.entity.Picture;
import com.example.whitecollar.entity.Shop;
import com.example.whitecollar.repository.PictureRepository;

@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	private PictureRepository pictureRepository;

	@Override
	public long deleteByShop(Shop shop) {
		return pictureRepository.deleteByShop(shop);
	}

	@Override
	public Picture addPicture(Picture picture) {
		return pictureRepository.save(picture);
	}

}
