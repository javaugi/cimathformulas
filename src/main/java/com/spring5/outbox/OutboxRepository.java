package com.spring5.outbox;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface OutboxRepository extends JpaRepository<Outbox, Long> {
    List<Outbox> findByProcessed(@Param("processed")  Boolean processed);
}
