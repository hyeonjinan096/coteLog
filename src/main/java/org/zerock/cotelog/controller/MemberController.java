package org.zerock.cotelog.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.cotelog.domain.Member;
import org.zerock.cotelog.dto.MemberJoinDTO;
import org.zerock.cotelog.service.MemberService;

@RestController
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public void joinGET(){

        log.info("join get...");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberJoinDTO memberJoinDTO) {
        log.info("로그인 요청...");
        log.info("로그인 정보: " + memberJoinDTO);

        try {
            Member member = memberService.findByMemberId(memberJoinDTO.getMid());

            if (!passwordEncoder.matches(memberJoinDTO.getMpw(), member.getMpw())) {
                return ResponseEntity.status(401).body("비밀번호가 일치하지 않습니다.");
            }

            log.info("로그인 성공: " + member.getMid());
            return ResponseEntity.ok("로그인 성공");

        } catch (MemberService.MidExistException e) {
            return ResponseEntity.status(404).body("아이디가 존재하지 않습니다.");
        }
    }

//    logout


}
