package kr.rang2ne.mobile.contents.delivery.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by gswon on 15. 11. 30.
 */
public interface MemberRepository extends JpaRepository<Member, String>, JpaSpecificationExecutor<Member>, CustomMemberRepository {
    List<Member> findByIdLike(String id);
}
