package kr.rang2ne.mobile.contents.delivery.member;

import com.mysema.query.jpa.JPQLQuery;
import kr.rang2ne.mobile.contents.delivery.contents.QContents;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gswon on 15. 11. 30.
 */
public class MemberRepositoryImpl extends QueryDslRepositorySupport implements CustomMemberRepository {
    public MemberRepositoryImpl(){super(Member.class);}

    @Override
    public List<Member> findAllKeyword(String keyword) {
        QMember member = QMember.member;
        JPQLQuery query = from(member);
        return query.where(member.id.like("%"+keyword+"%")).fetchAll().list(member);
    }

    @Override
    public List<Member> findHasContentSize4Over() {

        QMember member = QMember.member;
        QContents contents = QContents.contents;
        from(member).join(member.contentsList, contents)
                .groupBy(member.id)
                .having(member.count().gt(1))
                .list(member)
                .forEach(member1 -> System.out.println(member1.getId())
                );
        return new ArrayList<>();
//                .on(contents.count().gt(2)).fetchAll().list(member);
    }
}
