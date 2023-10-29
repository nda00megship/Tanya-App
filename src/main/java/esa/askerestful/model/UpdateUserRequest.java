package esa.askerestful.model;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserRequest {

    @Size(max = 100)
    private String username;

    @Size(max = 100)
    private String email;

    @Size(max = 9)
    private String password;
}
