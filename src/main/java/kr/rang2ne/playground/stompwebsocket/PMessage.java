package kr.rang2ne.playground.stompwebsocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gswon on 15. 12. 5.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PMessage {
    private String content;
}
