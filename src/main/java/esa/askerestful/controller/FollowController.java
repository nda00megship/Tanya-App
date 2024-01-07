package esa.askerestful.controller;

import esa.askerestful.entity.User;
import esa.askerestful.model.FollowRequest;
import esa.askerestful.model.FollowingResponse;
import esa.askerestful.model.WebResponse;
import esa.askerestful.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping(
            path = "/api/follow",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<FollowingResponse>> followUser(User user ,
            @RequestBody FollowRequest followRequest) {
        try {
            followService.followByUsername(
                    user ,
                    followRequest.getFollowerUsername(),
                    followRequest.getFollowedUsername()
            );

            FollowingResponse followingResponse = new FollowingResponse(
                    followRequest.getFollowerUsername(),
                    followRequest.getFollowedUsername()
            );
            WebResponse<FollowingResponse> response = WebResponse.<FollowingResponse>builder()
                    .data(followingResponse)
                    .build();

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            WebResponse<FollowingResponse> errorResponse = WebResponse.<FollowingResponse>builder()
                    .errors(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping(
            path = "/api/unfollow",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<FollowingResponse>> unfollowUser(User user,
                                                                       @RequestBody FollowRequest followRequest) {
        try {
            followService.unfollowByUsername(
                    user,
                    followRequest.getFollowerUsername(),
                    followRequest.getFollowedUsername()
            );

            FollowingResponse unfollowResponse = new FollowingResponse(
                    followRequest.getFollowerUsername(),
                    followRequest.getFollowedUsername()
            );
            WebResponse<FollowingResponse> response = WebResponse.<FollowingResponse>builder()
                    .data(unfollowResponse)
                    .build();

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            WebResponse<FollowingResponse> errorResponse = WebResponse.<FollowingResponse>builder()
                    .errors(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    public void unfollowByUsername(User user, String followerUsername, String followedUsername) {
        // Implementasi logika penghapusan hubungan follow
        followService.unfollowByUsername(user, followerUsername, followedUsername);
    }

}
