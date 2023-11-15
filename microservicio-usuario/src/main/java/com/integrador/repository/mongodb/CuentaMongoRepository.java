package com.integrador.repository.mongodb;

import com.integrador.domain.mongodb.CuentaMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaMongoRepository extends MongoRepository<CuentaMongo,String> {
}
