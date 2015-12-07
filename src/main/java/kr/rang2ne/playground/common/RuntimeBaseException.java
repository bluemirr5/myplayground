package kr.rang2ne.playground.common;

/**
 * Created by gswon on 15. 12. 7.
 */
public abstract class RuntimeBaseException extends RuntimeException {
    protected abstract Integer getDetailErrorCode();
}
