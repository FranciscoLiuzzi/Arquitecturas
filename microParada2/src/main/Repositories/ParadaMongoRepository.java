package main.Repositories;

import org.springframework.stereotype.Repository;
import main.Objects.ParadaMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ParadaMongoRepository extends ParadaMongoRepositoryCustom, MongoRepository<ParadaMongo, String>{}