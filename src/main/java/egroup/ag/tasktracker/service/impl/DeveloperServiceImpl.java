package egroup.ag.tasktracker.service.impl;

import egroup.ag.tasktracker.dto.CreateDeveloperModel;
import egroup.ag.tasktracker.dto.DeveloperDto;
import egroup.ag.tasktracker.entity.DeveloperEntity;
import egroup.ag.tasktracker.repository.DeveloperRepository;
import egroup.ag.tasktracker.service.DeveloperService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

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
}
