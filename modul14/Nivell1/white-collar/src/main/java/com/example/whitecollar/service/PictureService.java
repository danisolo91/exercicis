package com.example.whitecollar.service;

import com.example.whitecollar.entity.Picture;
import com.example.whitecollar.entity.Shop;

public interface PictureService {

	public Picture addPicture(Picture picture);

	public long deleteByShop(Shop shop);

}
