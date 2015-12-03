package kr.rang2ne.mobile.contents.delivery.security;

import kr.rang2ne.mobile.contents.delivery.member.Member;
import kr.rang2ne.mobile.contents.delivery.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by gswon on 15. 12. 1.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findOne(id);
        if(member == null) {
            throw new UsernameNotFoundException(id);
        }

        return new UserDetailsImpl(member);
    }
}
