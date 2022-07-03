package com.hello.project.domain.subscribe;

import com.hello.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Query(value="SELECT new com.hello.project.domain.subscribe.SubscribeRespDto(u.id,u.username,u.profileImageUrl)" +
                 " FROM Subscribe s JOIN s.toUser u  ON s.toUser.id = u.id WHERE s.fromUser.id=:fromId")
    List<SubscribeRespDto> subscribeList(@Param("fromId") Long fromId);

    @Query(value = "SELECT COUNT(s) FROM Subscribe s WHERE s.fromUser.id = :fromId AND s.toUser.id = :toId")
    int subscribeState(@Param("fromId") Long fromId, @Param("toId")Long toId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM Subscribe s WHERE s.fromUser.id = :fromId AND s.toUser.id = :toId")
    void unSubscribe(@Param("fromId") Long fromId, @Param("toId")Long toId);
    
//    @Query(value = "SELECT COUNT(s) FROM Subscribe s WHERE a.fromUser.id = :fromId AND s.toUser.id = :toId")
//    int subscribes(@Param("fromId") Long fromId);

}
