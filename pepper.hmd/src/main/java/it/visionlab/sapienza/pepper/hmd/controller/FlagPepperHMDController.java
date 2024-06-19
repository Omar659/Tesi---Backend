package it.visionlab.sapienza.pepper.hmd.controller;


import it.visionlab.sapienza.pepper.hmd.model.FlagPepperHMD;
import it.visionlab.sapienza.pepper.hmd.service.FlagPepperHMDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flagPepperHMD")
public class FlagPepperHMDController {

    private final FlagPepperHMDService flagPepperHMDService;

    @Autowired
    public FlagPepperHMDController(FlagPepperHMDService flagPepperHMDService) {
        this.flagPepperHMDService = flagPepperHMDService;
    }

    @GetMapping("/getFlag")
    public FlagPepperHMD getFlag(@RequestParam(required = true) String flagName) {
        return flagPepperHMDService.getFlag(flagName);
    }

    @PostMapping("/setFlag")
    public void setFlag(@RequestParam(required = true) String flagName) {
        flagPepperHMDService.setFlag(flagName);
    }

    @PutMapping("/switchFlag")
    public void switchFlag(@RequestParam(required = true) String flagName) {
        flagPepperHMDService.switchFlag(flagName);
    }

}
