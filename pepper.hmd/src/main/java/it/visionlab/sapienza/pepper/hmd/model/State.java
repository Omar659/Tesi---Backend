package it.visionlab.sapienza.pepper.hmd.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@Document(collection = "flagPepperHMD")
public class State {
    @Id
    private String stateId;
    private Boolean flagPepper;
    private Boolean flagHMD;
    private String stateName;
    private String chosenPlace;

    public State(String stateId, Boolean flagPepper, Boolean flagHMD, String stateName, String chosenPlace) {
        this.stateId = stateId;
        this.flagPepper = flagPepper;
        this.flagHMD = flagHMD;
        this.stateName = stateName;
        this.chosenPlace = chosenPlace;
    }
}
