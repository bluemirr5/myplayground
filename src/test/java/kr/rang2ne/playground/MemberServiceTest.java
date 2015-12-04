package kr.rang2ne.playground;

import kr.rang2ne.playground.member.Member;
import kr.rang2ne.playground.member.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by gswon on 15. 11. 30.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

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

