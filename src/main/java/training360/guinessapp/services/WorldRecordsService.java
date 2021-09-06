package training360.guinessapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training360.guinessapp.dto.BeatWorldRecordCommand;
import training360.guinessapp.dto.BeatWorldRecordDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;
import training360.guinessapp.entities.Recorder;
import training360.guinessapp.entities.WorldRecord;
import training360.guinessapp.repositories.RecordersRepository;
import training360.guinessapp.repositories.WorldRecordsRepository;

@Service
public class WorldRecordsService {

    private ModelMapper modelMapper;

    private RecordersRepository recordersRepository;

    private WorldRecordsRepository worldRecordsRepository;

    public WorldRecordsService(ModelMapper modelMapper, RecordersRepository recordersRepository, WorldRecordsRepository worldRecordsRepository) {
        this.modelMapper = modelMapper;
        this.recordersRepository = recordersRepository;
        this.worldRecordsRepository = worldRecordsRepository;
    }


    public WorldRecordDto saveWorldRecord(WorldRecordCreateCommand command) {
        Recorder recorder = getRecorder(command.getRecorderId());
        WorldRecord worldRecord = worldRecordsRepository.save(new WorldRecord(
                command.getDescription(),
                command.getValue(),
                command.getUnitOfMeasure(),
                command.getDateOfRecord(),
                recorder));

        return new WorldRecordDto(
                worldRecord.getId(),
                worldRecord.getDescription(),
                worldRecord.getValue(),
                worldRecord.getUnitOfMeasure(),
                worldRecord.getDateOfRecord(),
                worldRecord.getRecorder().getName());
    }


    @Transactional
    public BeatWorldRecordDto beatWorldRecord(Long id, BeatWorldRecordCommand command) {
        WorldRecord worldRecord = worldRecordsRepository.getById(id);
        Recorder oldRecorder = worldRecord.getRecorder();
        Recorder newRecorder = getRecorder(command.getRecorderId());


        Double valueDifference = command.getValue() - worldRecord.getValue();

        if(valueDifference < 0){
            throw new IllegalArgumentException("Record smaller than precious");
        }

        BeatWorldRecordDto beatWorldRecordDto = new BeatWorldRecordDto(
                worldRecord.getDescription(),
                worldRecord.getUnitOfMeasure(),
                oldRecorder.getName(),
                worldRecord.getValue(),
                newRecorder.getName(),
                command.getValue(),
                valueDifference
                );

        worldRecord.setRecorder(newRecorder);
        worldRecord.setValue(command.getValue());

        return beatWorldRecordDto;
    }

    private Recorder getRecorder(Long id) {
        Recorder recorder = recordersRepository.getById(id);
        if (recorder == null){
            throw new IllegalArgumentException("Recorder not found");
        }
        return recorder;
    }

}
