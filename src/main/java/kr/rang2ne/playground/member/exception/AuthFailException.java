package kr.rang2ne.playground.member.exception;

import kr.rang2ne.playground.common.RuntimeBaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gswon on 15. 12. 7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthFailException extends RuntimeBaseException {
    public static int ID_FAIL = 1;
    public static int PASS_FAIL = 2;

    private Integer errorCode;

    @Override
    public Integer getDetailErrorCode() {
        return this.errorCode;
    }
}
