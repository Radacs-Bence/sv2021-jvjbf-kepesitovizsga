package training360.guinessapp.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;
import training360.guinessapp.entities.Recorder;
import training360.guinessapp.repositories.RecordersRepository;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class RecordersService {

    private ModelMapper modelMapper;

    private RecordersRepository recordersRepository;

    public RecordersService(ModelMapper modelMapper, RecordersRepository recordersRepository) {
        this.modelMapper = modelMapper;
        this.recordersRepository = recordersRepository;
    }

    public RecorderDto saveRecorder(RecorderCreateCommand command) {
        Recorder recorder = recordersRepository.save(new Recorder(command.getName(), command.getDateOfBirth()));

        return modelMapper.map(recorder, RecorderDto.class);
    }

    public List<RecorderShortDto> returnCertainRecorders() {
        List<Recorder> recorders = recordersRepository.getCertainRecorders();

        Type targetListType = new TypeToken<List<RecorderShortDto>>() {
        }.getType();
        return modelMapper.map(recorders, targetListType);
    }
}
