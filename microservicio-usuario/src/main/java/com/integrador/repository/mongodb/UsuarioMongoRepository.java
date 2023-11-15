package com.integrador.repository.mongodb;

import com.integrador.domain.mongodb.UsuarioMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioMongoRepository extends MongoRepository<UsuarioMongo, String> {
}
