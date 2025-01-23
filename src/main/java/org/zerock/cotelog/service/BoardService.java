package org.zerock.cotelog.service;

import org.zerock.cotelog.dto.BoardDTO;
import org.zerock.cotelog.dto.PageRequestDTO;
import org.zerock.cotelog.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
