package kr.rang2ne.mobile.contents.delivery.member;

import java.util.List;

/**
 * Created by gswon on 15. 11. 30.
 */
public interface CustomMemberRepository {
    List<Member> findAllKeyword(String keyword);
    List<Member> findHasContentSize4Over();
}
