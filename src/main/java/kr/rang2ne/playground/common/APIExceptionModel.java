package kr.rang2ne.playground.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by gswon on 15. 12. 7.
 */
@Data
@AllArgsConstructor
public class APIExceptionModel {
    private final int errorCode;
    private final String message;
}
