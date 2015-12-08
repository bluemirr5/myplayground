package kr.rang2ne.playground.websocketchat;

import lombok.Data;

import java.util.Date;

/**
 * Created by gswon on 15. 12. 8.
 */
@Data
public class Message {
    private String message;
    private String fromUser;
    private Date pubDate;
}
