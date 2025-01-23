package org.zerock.cotelog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.cotelog.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //@Query 어노테이션을 사용하여 bno 파라미터에 @Param("bno")를 추
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable);
}