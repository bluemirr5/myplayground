package kr.rang2ne.playground.member.customrepository;

import kr.rang2ne.playground.member.model.Member;

import java.util.List;

/**
 * Created by gswon on 15. 11. 30.
 */
public interface CustomMemberRepository {
    List<Member> findAllKeyword(String keyword);
    List<Member> findHasContentSize4Over();
}
