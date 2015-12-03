package kr.rang2ne.mobile.contents.delivery.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gswon on 15. 11. 30.
 */
@Service
@Slf4j
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public List<Member> findByIdLike(String id) {
        return memberRepository.findByIdLike(id);
    }

    public List<Member> find(String keyword) {
        return memberRepository.findAllKeyword(keyword);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void save(Member member) throws Exception {
        if(memberRepository.exists(member.getId())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
//        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public Member findById(String id) {
        return memberRepository.findOne(id);
    }


    public void testPrint() {
        memberRepository.findAll().forEach(member1 -> log.debug(member1.getPassword()));
        memberRepository.findAll().forEach(member -> member.setPassword("setted3"));
        memberRepository.findAll().forEach(member1 -> log.debug(member1.getPassword()));
        memberRepository.findHasContentSize4Over().forEach(member -> log.debug(member.getId()));
        memberRepository.findAll().forEach(member -> log.debug(""+member.getContentsList().size()));
    }
}
