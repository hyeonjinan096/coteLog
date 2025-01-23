package org.zerock.cotelog.service;

import org.zerock.cotelog.domain.Member;
import org.zerock.cotelog.dto.MemberJoinDTO;

public interface MemberService {

    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO)throws MidExistException ;

    Member findByMemberId(String id) throws MidExistException;
}
