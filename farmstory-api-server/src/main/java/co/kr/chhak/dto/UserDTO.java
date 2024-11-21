package co.kr.chhak.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {
    private String uid;
    private String pass;
    private String name;
    private String nick;
    private String email;
    private String hp;

    @Builder.Default
    private String role = "USER";
    private String zip;
    private String addr1;
    private String addr2;
    private String regDate;

}
