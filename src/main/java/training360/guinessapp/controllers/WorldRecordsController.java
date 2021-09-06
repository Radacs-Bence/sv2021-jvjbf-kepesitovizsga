package training360.guinessapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import training360.guinessapp.dto.BeatWorldRecordCommand;
import training360.guinessapp.dto.BeatWorldRecordDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;
import training360.guinessapp.services.WorldRecordsService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/worldrecords")
public class WorldRecordsController {

    private WorldRecordsService worldRecordsService;

    public WorldRecordsController(WorldRecordsService worldRecordsService) {
        this.worldRecordsService = worldRecordsService;
    }

    @PostMapping
    public WorldRecordDto saveWorldRecord(@Valid @RequestBody WorldRecordCreateCommand command){
        return worldRecordsService.saveWorldRecord(command);
    }

    @PutMapping("/api/worldrecords/{id}/beatrecords")
    public BeatWorldRecordDto beatWorldRecord(@PathVariable Long id, @Valid @RequestBody BeatWorldRecordCommand command){
        return worldRecordsService.beatWorldRecord(id, command);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> notEnoughSpaces(IllegalArgumentException e){
        Problem problem = Problem.builder()
                .withType(URI.create("worldrecords/not-found"))
                .withTitle("No recorder found with id")
                .withStatus(Status.NOT_FOUND)
                .withDetail(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
