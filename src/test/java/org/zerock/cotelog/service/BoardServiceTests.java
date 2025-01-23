package org.zerock.cotelog.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.cotelog.dto.BoardDTO;
import org.zerock.cotelog.dto.PageRequestDTO;
import org.zerock.cotelog.dto.PageResponseDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    BoardService boardService;

    @BeforeEach
    public void testRegister() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Sample Title")
                .content("Sample Content")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno " + bno);
    }

    @Test
    public void testModify() {

        BoardDTO newBoardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated Title 101")
                .content("Updated Content 101")
                .build();

        boardService.modify(newBoardDTO);

        BoardDTO result = boardService.readOne(101L); // 보드 조회 메서드 호출
        assertEquals("Updated Title 101", result.getTitle());
        assertEquals("Updated Content 101", result.getContent());
    }

    @Test
    public void testRemove() {
        boardService.remove(101L);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

    }
}

