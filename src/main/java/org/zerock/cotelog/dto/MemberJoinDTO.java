package org.zerock.cotelog.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class MemberJoinDTO {

    private String mid;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;

}
