package kr.rang2ne.playground.member;

import kr.rang2ne.playground.member.exception.AuthFailException;
import kr.rang2ne.playground.member.exception.DuplicationIdException;
import kr.rang2ne.playground.member.model.Member;
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

    public void save(Member member) {
        if(memberRepository.exists(member.getId())) {
            throw new DuplicationIdException();
        }
        memberRepository.save(member);
    }

    public Member auth(Member member) {
        Member storedMember = memberRepository.findOne(member.getId());
        if(storedMember == null ||
                storedMember.getId() == null ||
                "".equals(storedMember.getId())) {
            throw new AuthFailException(AuthFailException.ID_FAIL);
        } else if(storedMember.checkIdPass(member.getId(), member.getPassword())) {
            return storedMember;
        } else {
            throw new AuthFailException(AuthFailException.PASS_FAIL);
        }
    }

    public Member findById(String id) {
        return memberRepository.findOne(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> findByIdLike(String id) {
        return memberRepository.findByIdLike(id);
    }

    public List<Member> find(String keyword) {
        return memberRepository.findAllKeyword(keyword);
    }
}
