package com.example.whitecollar.controller;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.whitecollar.entity.Picture;
import com.example.whitecollar.entity.Shop;
import com.example.whitecollar.service.PictureService;
import com.example.whitecollar.service.ShopService;

@RestController
@RequestMapping("/v1/shops")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private PictureService pictureService;

	/**
	 * Retorna una llista de totes les botigues.
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> list() {
		return ResponseEntity.ok(shopService.getAllShops());
	}

	/**
	 * Afegeix una nova botiga i retorna la resposta del servidor.
	 * 
	 * @param shop
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> addShop(@Valid @RequestBody Shop shop) {
		Shop newShop = shopService.addShop(shop);

		return ResponseEntity.created(URI.create("/shops/" + newShop.getId()))
				.body(Collections.singleton("Botiga creada"));
	}

	/**
	 * Afegeix un quadre a una botiga i retorna la resposta del servidor.
	 * 
	 * @param picture
	 * @param id
	 * @return
	 */
	@PostMapping("/{id}/pictures")
	public ResponseEntity<?> addPicture(@Valid @RequestBody Picture picture, @PathVariable int id) {
		Optional<Shop> shop = shopService.getShopById(id);

		if (shop.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		if (shop.get().getPictures().size() == shop.get().getMaxCapacity()) {
			return ResponseEntity.badRequest().body(
					Collections.singleton("Ja no hi caben més quadres. La botiga està a la seva capacitat màxima."));
		}

		picture.setShop(shop.get());
		pictureService.addPicture(picture);

		return ResponseEntity.ok(Collections.singleton("Quadre afegit a la botiga"));
	}

	/**
	 * Retora una llista amb tots els quadres d'una botiga.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/pictures")
	public ResponseEntity<?> listPictures(@PathVariable int id) {
		Optional<Shop> shop = shopService.getShopById(id);

		if (shop.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(shop.get().getPictures());
	}

	/**
	 * Elimina tots els quadres d'una botiga i retorna la resposta del servidor.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}/pictures")
	public ResponseEntity<?> deletePictures(@PathVariable int id) {
		Optional<Shop> shop = shopService.getShopById(id);

		if (shop.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		pictureService.deleteByShop(shop.get());

		return ResponseEntity.ok(Collections.singleton("Quadres eliminats"));
	}

	/**
	 * Mètode per gestionar els errors de validació. Retorna al client els errors de
	 * validació en format JSON.
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
