package egroup.ag.tasktracker.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeveloperRequestModel {

    @NotNull(message = "Name cannot be null")
    private String name;
}
