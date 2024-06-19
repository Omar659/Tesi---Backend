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
public class FlagPepperHMD {
    @Id
    private String flagName;
    private Boolean value;  // 0 = Pepper; 1 = HMD

    public FlagPepperHMD(String flagName, boolean value) {
        this.flagName = flagName;
        this.value = value;
    }
}
