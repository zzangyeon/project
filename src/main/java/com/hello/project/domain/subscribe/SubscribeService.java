package com.hello.project.domain.subscribe;

import com.hello.project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public Subscribe saveSubscribe(Long fromUserId, Long toUserId) {

        User fromUser = new User(fromUserId);
        User toUser = new User(toUserId);

        Subscribe subscribe = new Subscribe();
        subscribe.setFromUser(fromUser);
        subscribe.setToUser(toUser);

        Subscribe subscribeEntity = subscribeRepository.save(subscribe);

        return subscribeEntity;
    }

    @Transactional
    public void deleteSubscribe(Long fromUserId, Long toUserId) {
        subscribeRepository.unSubscribe(fromUserId,toUserId);
    }

    @Transactional(readOnly = true)
    public List<SubscribeRespDto> subscribeList(Long id) {
        return subscribeRepository.subscribeList(id);
    }
}
