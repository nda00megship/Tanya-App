package esa.askerestful.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequest {

    @NotBlank
    private String followerUsername;

    @NotBlank
    private String followedUsername;
}
