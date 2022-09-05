package com.example.crudmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.crudmongodb.model.ProductDTO;

@Repository
public interface IProductDAO extends MongoRepository<ProductDTO, String>{

}
