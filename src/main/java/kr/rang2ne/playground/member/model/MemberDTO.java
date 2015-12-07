package kr.rang2ne.playground.member.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by gswon on 15. 12. 6.
 */
public class MemberDTO {
    @Data
    public static class Save {
        @NotNull
        private String id;
        @NotNull
        private String password;
    }
    @Data
    public static class Login {
        @NotNull
        private String id;
        @NotNull
        private String password;
    }
}
