package com.example.whitecollar.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shops")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "El nom de la botiga es obligatori")
	@Column(name = "name")
	private String name;

	@Min(value = 1, message = "La capacitat màxima ha de ser com a mínim 1")
	@Column(name = "max_capacity")
	private int maxCapacity;

	/**
	 * Definim aquest atribut per tenir un "accés directe" als quadres de la botiga.
	 * Carreguem amb LAZY per que Hibernate faci la consulta a la BD nomès si
	 * utilitzem el mètode get. Posem orphanRemoval a true, per eliminar els quadres
	 * si eliminem la botiga i no quedin valors orfes a 'shop_id' en la taula
	 * pictures. JsonIgnore per no mostrar els quadres quan consultem una botiga.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Picture> pictures;

	public Shop() {
	}

	public Shop(int id, String name, int maxCapacity) {
		this.id = id;
		this.name = name;
		this.maxCapacity = maxCapacity;
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

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	@Override
	public String toString() {
		return "Botiga [id=" + id + ", name=" + name + ", maxCapacity=" + maxCapacity + "]";
	}

}
