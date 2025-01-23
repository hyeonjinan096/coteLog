package org.zerock.cotelog.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.cotelog.dto.BoardDTO;
import org.zerock.cotelog.dto.PageRequestDTO;
import org.zerock.cotelog.dto.PageResponseDTO;
import org.zerock.cotelog.service.BoardService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "Get list of Boards", notes = "Get 방식으로 보드 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<BoardDTO>> list(PageRequestDTO pageRequestDTO) {

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info("Fetched Board List: {}", responseDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation(value = "Post Board", notes = "Post 방식으로 보드 등록")
    @PostMapping("/register")
    public ResponseEntity<String> registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info("board POST register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return ResponseEntity.ok("redirect:/board/register");
        }

        log.info(boardDTO);

        Long bno  = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return ResponseEntity.ok( "redirect:/board/list");
    }

    @ApiOperation(value = "Read Board", notes = "Get 방식으로 보드 조회")
    @GetMapping("/read" )
    public ResponseEntity<BoardDTO> read(Long bno) {

        BoardDTO boardDTO = boardService.readOne(bno);

        return ResponseEntity.ok(boardDTO);
    }

    @ApiOperation(value = "Delete Board", notes = "DELETE 방식으로 보드 삭제")
    @DeleteMapping("/remove")
    public Map<String, Long> remove(BoardDTO boardDTO) {

        Long bno  = boardDTO.getBno();
        log.info("remove post.. " + bno);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno", bno);

        return resultMap;

    }


}
