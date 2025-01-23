package org.zerock.cotelog.service;

import org.zerock.cotelog.dto.PageRequestDTO;
import org.zerock.cotelog.dto.PageResponseDTO;
import org.zerock.cotelog.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
