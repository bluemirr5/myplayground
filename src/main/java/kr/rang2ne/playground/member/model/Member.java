package kr.rang2ne.playground.member.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.rang2ne.playground.contents.Contents;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<Contents> contentsList;

    public boolean checkIdPass(String id, String password) {
        return this.id.equals(id)  && this.password.equals(password);
    }
}
