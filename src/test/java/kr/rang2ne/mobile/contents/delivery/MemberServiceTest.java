package kr.rang2ne.mobile.contents.delivery;

import kr.rang2ne.mobile.contents.delivery.member.Member;
import kr.rang2ne.mobile.contents.delivery.member.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gswon on 15. 11. 30.
 */
//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;


//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    WebApplicationContext wac;
//    @Autowired
//    private FilterChainProxy filterChainProxy;
//    MockMvc mockMvc;
    @Before
    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//                .addFilter(filterChainProxy)
//                .build();
    }

//    @Test
//    public void encryptPasswordTest() throws Exception {
//        final String id = "Test";
//        final String password = "1234";
//        Member member = new Member();
//        member.setId(id);
//        member.setPassword(password);
//
//        memberService.save(member);
//
//        Member findMember = memberService.findById(id);
//        Assert.isTrue(passwordEncoder.matches(password, findMember.getPassword()));
//
//    }

    @Test(expected = IllegalStateException.class)
    public void saveDuplicateMember() throws Exception {
        Member member = new Member();
        member.setId("abc");
        member.setPassword("asdfasdf");
        memberService.save(member);

        Member dupMember = new Member();
        dupMember.setId("abc");
        dupMember.setPassword("111asdfasdf");
        memberService.save(dupMember);
    }
}

