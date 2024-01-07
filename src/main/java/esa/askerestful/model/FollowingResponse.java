package esa.askerestful.model;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowingResponse {

    private String followerUsername;

    private String followedUsername;
}
