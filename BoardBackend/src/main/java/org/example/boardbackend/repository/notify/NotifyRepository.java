package org.example.boardbackend.repository.notify;

import org.example.boardbackend.model.entity.notify.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotifyRepository extends JpaRepository<Notify, Long> {
    // todo 읽지 않은 알림 확인
    public List<Notify> findByUserIdAndIsRead(String userId, String isRead);

    @Modifying
    @Transactional
    @Query("UPDATE Notify n SET n.isRead = 'Y' WHERE n.userId = :userId")
    public void updateByUserId(@Param("userId") String userId);
}
