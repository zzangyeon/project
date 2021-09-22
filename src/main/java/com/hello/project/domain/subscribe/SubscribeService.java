package com.hello.project.domain.subscribe;

import com.hello.project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public Subscribe saveSubscribe(SubscribeDto subscribeDto) {

        User fromUser = new User(subscribeDto.getFromId());
        User toUser = new User(subscribeDto.getToId());
        Subscribe subscribe = new Subscribe();

        subscribe.setFromUser(fromUser);
        subscribe.setToUser(toUser);

        Subscribe subscribeEntity = subscribeRepository.save(subscribe);

        return subscribeEntity;
    }
}
