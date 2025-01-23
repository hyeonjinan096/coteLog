package org.zerock.cotelog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.cotelog.domain.Board;
import org.zerock.cotelog.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
}
