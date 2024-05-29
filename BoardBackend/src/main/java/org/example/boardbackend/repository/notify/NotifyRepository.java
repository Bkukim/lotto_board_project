package org.example.boardbackend.repository.notify;

import org.example.boardbackend.model.entity.notify.Notify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifyRepository extends JpaRepository<Notify, Long> {

}
