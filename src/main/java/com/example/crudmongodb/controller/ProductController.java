package com.example.crudmongodb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudmongodb.model.ProductDTO;
import com.example.crudmongodb.repository.IProductDAO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private IProductDAO repository;

	@PostMapping("/product")
	public ResponseEntity<ProductDTO> create(@Validated @RequestBody ProductDTO p) {

		return new ResponseEntity<>(repository.insert(p), HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> readAll() {

		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable String id, @Validated @RequestBody ProductDTO p) {

		Optional<ProductDTO> opt = repository.findById(id);
		if (opt.isPresent()) {

			ProductDTO productDTO = opt.get();
			productDTO.setName(p.getName());
			productDTO.setPrice(p.getPrice());
			productDTO.setExpiration_date(p.getExpiration_date());

			return new ResponseEntity<>(repository.save(productDTO), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {

		repository.deleteById(id);
		return ResponseEntity.ok("Product with id " + id + " was deleted");

	}

}
