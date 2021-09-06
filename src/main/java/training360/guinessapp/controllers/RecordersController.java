package training360.guinessapp.controllers;

import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;
import training360.guinessapp.services.RecordersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recorders")
public class RecordersController {

    private RecordersService recordersService;

    public RecordersController(RecordersService recordersService) {
        this.recordersService = recordersService;
    }

    @PostMapping
    public RecorderDto saveRecorder(@Valid @RequestBody RecorderCreateCommand command){
        return recordersService.saveRecorder(command);
    }

    @GetMapping
    public List<RecorderShortDto> returnCertainRecorders(){
        return recordersService.returnCertainRecorders();
    }
}
