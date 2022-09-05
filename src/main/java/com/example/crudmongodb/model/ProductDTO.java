package com.example.crudmongodb.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "products")
public class ProductDTO {

	@Id
	private String _id;

	private String name;

	private Double price;

	private LocalDate expiration_date;

}
