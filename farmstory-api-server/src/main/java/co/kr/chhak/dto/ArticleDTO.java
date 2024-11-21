package co.kr.chhak.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ArticleDTO {

    private int no;
    private String cate;

    @Builder.Default
    private int comment = 0;
    private String title;
    private String content;
    private String writer;

    @Builder.Default
    private int file = 0;
    @Builder.Default
    private int hit = 0;

    private String regip;
    private String regDate;


}
