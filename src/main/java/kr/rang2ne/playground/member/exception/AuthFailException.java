package kr.rang2ne.playground.member.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gswon on 15. 12. 7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthFailException extends IllegalArgumentException {
    public static int ID_FAIL = 1;
    public static int PASS_FAIL = 2;

    private int failStatusCode;
}
