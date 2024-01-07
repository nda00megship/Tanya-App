package esa.askerestful.service;

import esa.askerestful.entity.Follow;
import esa.askerestful.entity.User;
import esa.askerestful.repository.FollowRepository;
import esa.askerestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class FollowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;


    public void followByUsername(User user ,String followerUsername, String followedUsername) {
        User follower = userRepository.findByUsername(followerUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Follower tidak ditemukan : " + followerUsername));

        User followed = userRepository.findByUsername(followedUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Followed tidak ditemukan : " + followedUsername));

        if (follower.equals(followed)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT , "Tidak bisa follow diri sendiri");
        }

        if (!isAlreadyFollowing(follower, followed)) {
            Follow newFollow = new Follow();
            newFollow.setIdFollowing(UUID.randomUUID().toString());
            newFollow.setFollowed(followed);
            newFollow.setFollower(follower);
            followRepository.save(newFollow);
        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT , "sudah di follow");
        }
    }

    private boolean isAlreadyFollowing(User follower, User followed) {
        return followRepository.existsByFollowerAndFollowed(follower, followed);
    }
}
