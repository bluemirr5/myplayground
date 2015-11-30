package kr.rang2ne.mobile.contents.delivery.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gswon on 15. 11. 30.
 */
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findByIdLike(String id) {
        return memberRepository.findByIdLike(id);
    }

    public List<Member> find(String keyword) {
        return memberRepository.findAllKeyword(keyword);
    }

    public void save(Member member) throws Exception {
        if(memberRepository.exists(member.getId())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        memberRepository.save(member);
    }

    public Member findById(String id) {
        return memberRepository.findOne(id);
    }

//    @Transactional
    public void setPassword() {
        memberRepository.findAll().forEach(member1 -> System.out.println(member1.getPassword()));
        memberRepository.findAll().forEach(member -> member.setPassword("setted3"));
        memberRepository.findAll().forEach(member1 -> System.out.println(member1.getPassword()));
        memberRepository.findHasContentSize4Over().forEach(member -> System.out.println(member.getId()));
    }

}
