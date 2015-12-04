package kr.rang2ne.playground.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by gswon on 15. 12. 4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecvContentsMessage {
    private String code;
    private String message;
    private Date recvDate;
}
