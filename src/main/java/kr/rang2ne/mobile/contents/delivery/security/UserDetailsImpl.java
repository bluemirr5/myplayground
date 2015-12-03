package kr.rang2ne.mobile.contents.delivery.security;

import kr.rang2ne.mobile.contents.delivery.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by gswon on 15. 12. 1.
 */
public class UserDetailsImpl extends User {
    public UserDetailsImpl(Member member) {
        super(member.getId(), member.getPassword(), authorities(member));
    }

    private static Collection<? extends GrantedAuthority> authorities(Member member) {
        List<GrantedAuthority> authorities = new ArrayList<>();
//        if(meber.level == admin)
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        else
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
