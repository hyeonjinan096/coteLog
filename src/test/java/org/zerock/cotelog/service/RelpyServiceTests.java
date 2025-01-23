package org.zerock.cotelog.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.cotelog.domain.Board;
import org.zerock.cotelog.domain.Reply;
import org.zerock.cotelog.dto.PageRequestDTO;
import org.zerock.cotelog.dto.PageResponseDTO;
import org.zerock.cotelog.dto.ReplyDTO;
import org.zerock.cotelog.repository.ReplyRepository;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@Transactional
public class RelpyServiceTests {

    @Autowired
    ReplyService replyService;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testRegister() {

        Long bno = 100L;

        IntStream.rangeClosed(1, 50).forEach(i -> {
            ReplyDTO replyDTO = ReplyDTO.builder()
                    .replyText("댓글..." + i)
                    .replyer("replyer" + i)
                    .bno(bno)
                    .build();

            log.info(replyService.register(replyDTO));
        });

    }

    //Test 확인해보기
    @Test
    public void testBoardReplies() {

        Long bno = 100L;

        IntStream.rangeClosed(1, 50).forEach(i -> {
            ReplyDTO replyDTO = ReplyDTO.builder()
                    .replyText("댓글..." + i)
                    .replyer("replyer" + i)
                    .bno(bno)
                    .build();

            log.info(replyService.register(replyDTO));
        });

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<ReplyDTO> result = replyService.getListOfBoard(bno, pageRequestDTO);

        result.getDtoList().forEach(replyDTO -> log.info(replyDTO));



//        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
//
//        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
//
//        result.getContent().forEach(reply -> log.info(reply));
    }
}
