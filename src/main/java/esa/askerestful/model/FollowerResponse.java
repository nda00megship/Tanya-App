package esa.askerestful.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowerResponse {

    private String followerUsername;

    private String followedUsername;
}

