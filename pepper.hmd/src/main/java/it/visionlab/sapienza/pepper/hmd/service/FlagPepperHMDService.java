package it.visionlab.sapienza.pepper.hmd.service;

import it.visionlab.sapienza.pepper.hmd.model.FlagPepperHMD;
import it.visionlab.sapienza.pepper.hmd.repository.FlagPepperHMDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlagPepperHMDService {

    private final FlagPepperHMDRepository flagPepperHMDRepository;

    @Autowired
    public FlagPepperHMDService(FlagPepperHMDRepository flagPepperHMDRepository) {
        this.flagPepperHMDRepository = flagPepperHMDRepository;
    }

    public void setFlag(String flagName) {
        flagPepperHMDRepository.setFlag(flagName);
    }

    public void switchFlag(String flagName) {
        flagPepperHMDRepository.switchFlag(flagName);
    }

    public FlagPepperHMD getFlag(String flagName) {
        return flagPepperHMDRepository.getFlag(flagName);
    }
}
