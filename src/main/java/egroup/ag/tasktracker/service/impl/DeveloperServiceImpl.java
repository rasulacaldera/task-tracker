package egroup.ag.tasktracker.service.impl;

import egroup.ag.tasktracker.dto.CreateDeveloperModel;
import egroup.ag.tasktracker.dto.DeveloperDto;
import egroup.ag.tasktracker.entity.DeveloperEntity;
import egroup.ag.tasktracker.repository.DeveloperRepository;
import egroup.ag.tasktracker.service.DeveloperService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public DeveloperDto createDeveloper(CreateDeveloperModel developer) {

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
}
