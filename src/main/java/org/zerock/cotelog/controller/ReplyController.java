package org.zerock.cotelog.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.cotelog.dto.ReplyDTO;
import org.springframework.validation.BindException;
import org.zerock.cotelog.service.ReplyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @ApiOperation(value = "Replies POST", notes = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> register(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult)throws BindException {

        log.info(replyDTO);

        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();

        Long rno = replyService.register(replyDTO);

        resultMap.put("rno",rno);

        return resultMap;
    }

    @ApiOperation(value = "Read Reply", notes = "GET 방식으로 특정 댓글 조회")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO( @PathVariable("rno") Long rno ){

        ReplyDTO replyDTO = replyService.read(rno);

        return replyDTO;
    }

    @ApiOperation(value = "Delete Reply", notes = "DELETE 방식으로 특정 댓글 삭제")
    @DeleteMapping("/{rno}")
    public Map<String,Long> remove( @PathVariable("rno") Long rno ){

        replyService.remove(rno);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno", rno);

        return resultMap;

    }


    @ApiOperation(value = "Modify Reply", notes = "PUT 방식으로 특정 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE )
    public Map<String,Long> remove( @PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO ){

        replyDTO.setRno(rno); //번호를 일치시킴

        replyService.modify(replyDTO);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno", rno);

        return resultMap;
    }



}
