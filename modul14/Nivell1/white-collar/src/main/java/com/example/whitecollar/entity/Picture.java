package com.example.whitecollar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pictures")
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "El nom del quadre es obligatori")
	@Column(name = "name")
	private String name;

	private double price;

	@NotNull(message = "La data d'entrada del quadre es obligatòria")
	@Column(name = "entry_date")
	private Date entryDate;

	/**
	 * Creem una clau forànea 'botiga_id' que apunti a la columna 'id' de la botiga.
	 * Aquesta relació no pot ser opcional.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;

	@Column(nullable = true)
	private String author;

	public Picture() {
	}

	public Picture(int id, String name, double price, Date entryDate, Shop shop, String author) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.entryDate = entryDate;
		this.shop = shop;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", name=" + name + ", price=" + price + ", entryDate=" + entryDate + ", botiga="
				+ shop + ", author=" + author + "]";
	}

}
