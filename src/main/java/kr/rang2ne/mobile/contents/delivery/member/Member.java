package kr.rang2ne.mobile.contents.delivery.member;

import kr.rang2ne.mobile.contents.delivery.contents.Contents;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by gswon on 15. 11. 30.
 */
@Data
@Entity
public class Member {
    @Id
    private String id;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Contents> contentsList;
}
