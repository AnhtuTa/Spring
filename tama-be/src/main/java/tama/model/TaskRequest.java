package tama.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    @NotNull(message="name cannot be null")
    private String name;

    @NotNull(message="status cannot be null")
    private String status;
}
