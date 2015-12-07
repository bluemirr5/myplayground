package kr.rang2ne.playground.contents;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.rang2ne.playground.member.model.Member;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by gswon on 15. 11. 30.
 */
@Data
@Entity
public class Contents {
    @Id
    private String id;
    private Boolean isPublic;
    private String title;
    private String filePath;

    private Date createDate;
    private Date modifyDate;

    @JsonBackReference
    @ManyToOne
    private Member member;

    public Boolean togglePublic() {
        this.setIsPublic(!this.getIsPublic());
        return this.getIsPublic();
    }
}
