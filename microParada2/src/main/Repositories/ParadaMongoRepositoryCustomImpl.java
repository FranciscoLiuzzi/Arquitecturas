package main.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import main.Objects.ParadaMongo;

public class ParadaMongoRepositoryCustomImpl implements ParadaMongoRepositoryCustom{
    
	@Autowired
    private MongoTemplate mongoTemplate;

    public ParadaMongo findByXAndY(String x, String y){
        Query query = new Query();
        query.addCriteria(Criteria.where("x").is(x).and("y").is(y));
        return mongoTemplate.findOne(query, ParadaMongo.class, "paradas");
    }
}