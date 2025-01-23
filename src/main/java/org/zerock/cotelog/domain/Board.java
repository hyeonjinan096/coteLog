package org.zerock.cotelog.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@ToString(exclude = "tags")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false) //컬럼의 길이와 null허용여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column
    private String problem_link;

    @Column(length = 50, nullable = false)
    private String writer;

//    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
//    @Builder.Default
//    private Set<BoardTag> tags = new HashSet<>();

//    public void setTag(List<BoardTag> list) {
//        this.tags.clear();
//
//        for (BoardTag boardTag : list) {
//            this.tags.add(boardTag);
//            boardTag.setParent(this, boardTag.getTag());
//        }
//    }

    public void change(String title, String content, String problem_link){
        this.title = title;
        this.content = content;
        this.problem_link = problem_link;
    }

}
