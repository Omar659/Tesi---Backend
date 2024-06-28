package it.visionlab.sapienza.pepper.hmd.repository;

import it.visionlab.sapienza.pepper.hmd.model.State;
import lombok.extern.java.Log;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import static it.visionlab.sapienza.pepper.hmd.constants.StartVariables.stateId;

@Log
@Repository
public class FlagPepperHMDRepository {
    private final MongoTemplate mongoTemplate;

    public FlagPepperHMDRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setState(Boolean flagPepper, Boolean flagHMD, String stateName, String chosenPlace) {
        State state = new State(stateId, flagPepper,flagHMD,stateName,chosenPlace);
        mongoTemplate.save(state);
    }

    public void activate(String who) {
        Query query = new Query(Criteria.where("stateId").is(stateId));
        State state = mongoTemplate.findOne(query, State.class);
        assert state != null;
        Update update;
        if (Objects.equals(who, "pepper")) {
            update = new Update().set("flagPepper", true);
        } else {
            update = new Update().set("flagHMD", true);
        }
        mongoTemplate.updateFirst(query, update, State.class);
    }

    public void deactivate(String who) {
        Query query = new Query(Criteria.where("stateId").is(stateId));
        State state = mongoTemplate.findOne(query, State.class);
        assert state != null;
        Update update;
        if (Objects.equals(who, "pepper")) {
            update = new Update().set("flagPepper", false);
        } else {
            update = new Update().set("flagHMD", false);
        }
        mongoTemplate.updateFirst(query, update, State.class);
    }

    public State getState() {
        Query query = new Query(Criteria.where("flagName").is(stateId));
        return mongoTemplate.findOne(query, State.class);
    }

    public void nextState(String stateName) {
        Query query = new Query(Criteria.where("stateId").is(stateId));
        State state = mongoTemplate.findOne(query, State.class);
        assert state != null;
        Update update = new Update().set("chosenPlace", stateName);
        mongoTemplate.updateFirst(query, update, State.class);
    }
}
