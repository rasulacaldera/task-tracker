package egroup.ag.tasktracker.service.impl;

import egroup.ag.tasktracker.constants.ErrorMessage;
import egroup.ag.tasktracker.dto.ApiError;
import egroup.ag.tasktracker.dto.DeveloperDto;
import egroup.ag.tasktracker.dto.DeveloperRequestModel;
import egroup.ag.tasktracker.entity.DeveloperEntity;
import egroup.ag.tasktracker.exception.CustomServiceException;
import egroup.ag.tasktracker.repository.DeveloperRepository;
import egroup.ag.tasktracker.service.DeveloperService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public DeveloperDto createDeveloper(DeveloperRequestModel developer) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        DeveloperEntity developerEntity = modelMapper.map(developer, DeveloperEntity.class);
        return modelMapper.map(developerRepository.save(developerEntity), DeveloperDto.class);
    }

    @Override
    public List<DeveloperDto> getAllDevelopers() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Type listType = new TypeToken<List<DeveloperDto>>() {
        }.getType();
        return modelMapper.map(developerRepository.findAll(), listType);
    }

    @Override
    public DeveloperDto getDeveloperById(long id) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        DeveloperDto developerDto = modelMapper.map(developerRepository.findById(id), DeveloperDto.class);
        if (developerDto == null) {
            throw CustomServiceException
                    .builder()
                    .error(new ApiError(ErrorMessage.DEVELOPER_NOT_FOUND, String.valueOf(id))
                    ).build();
        }
        return developerDto;
    }

    @Override
    public DeveloperDto updateDeveloperById(long id, DeveloperRequestModel developer) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Optional<DeveloperEntity> developerEntity = developerRepository.findById(id);

        if (developerEntity.isEmpty()) {
            throw CustomServiceException
                    .builder()
                    .error(new ApiError(ErrorMessage.DEVELOPER_NOT_FOUND, String.valueOf(id))
                    ).build();
        }
        developerEntity.get().setName(developer.getName());
        return modelMapper.map(developerRepository.save(developerEntity.get()), DeveloperDto.class);
    }

    @Override
    public void deleteDeveloperById(long id) {
        Optional<DeveloperEntity> developerEntity = developerRepository.findById(id);
        if (developerEntity.isEmpty()) {
            throw CustomServiceException
                    .builder()
                    .error(new ApiError(ErrorMessage.DEVELOPER_NOT_FOUND, String.valueOf(id))
                    ).build();
        }
        developerRepository.deleteById(id);
    }

}
