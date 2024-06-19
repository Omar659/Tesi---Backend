package it.visionlab.sapienza.pepper.hmd.repository;

import it.visionlab.sapienza.pepper.hmd.model.FlagPepperHMD;
import lombok.extern.java.Log;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static it.visionlab.sapienza.pepper.hmd.constants.StartVariables.flagPepperHMDStarter;

@Log
@Repository
public class FlagPepperHMDRepository {
    private final MongoTemplate mongoTemplate;

    public FlagPepperHMDRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setFlag(String flagName) {
        if (!existFlag(flagName)) {
            FlagPepperHMD flagPepperHMD = new FlagPepperHMD(flagName, flagPepperHMDStarter);
            mongoTemplate.save(flagPepperHMD);
            log.info("New Flag added and set");
        } else {
            Query query = new Query(Criteria.where("flagName").is(flagName));
            Update update = new Update().set("value", flagPepperHMDStarter);
            mongoTemplate.updateFirst(query, update, FlagPepperHMD.class);
            log.info("Flag value set to 0");
        }
    }

    public Boolean existFlag(String flagName) {
        Query query = new Query(Criteria.where("flagName").is(flagName));
        Boolean exist = mongoTemplate.exists(query, FlagPepperHMD.class);
        log.info("Flag " + flagName + (exist ? "exist" : "not exist"));
        return exist;
    }

    public void switchFlag(String flagName) {
        Query query = new Query(Criteria.where("flagName").is(flagName));
        FlagPepperHMD flag = mongoTemplate.findOne(query, FlagPepperHMD.class);

        if (flag != null) {
            // Get the actual value of the flag
            Boolean currentValue = flag.getValue();

            // Switch the value
            Boolean newValue = !currentValue;

            // Update the document in the DB with the new value
            Update update = new Update().set("value", newValue);
            mongoTemplate.updateFirst(query, update, FlagPepperHMD.class);

            log.info("Flag " + flagName + " switched to " + newValue);
        } else {
            log.warning("Flag " + flagName + " not found");
        }
    }

    public FlagPepperHMD getFlag(String flagName) {
        if (existFlag(flagName)) {
            Query query = new Query(Criteria.where("flagName").is(flagName));
            log.info("Flag " + flagName + " get");
            return mongoTemplate.findOne(query, FlagPepperHMD.class);
        } else {
            log.warning("Flag " + flagName + " not found");
            return null;
        }
    }
}
