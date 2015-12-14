package kr.rang2ne.playground;

import kr.rang2ne.playground.member.MemberService;
import kr.rang2ne.playground.member.exception.AuthFailException;
import kr.rang2ne.playground.member.exception.DuplicationIdException;
import kr.rang2ne.playground.member.model.Member;
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

//import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @Test
    public void MokMvcTest() throws Exception {
//        mockMvc.perform(
//                post("/member")
//                .accept("Adsafs")
//                .content("{asdfasdf}")
//        )
    }

    @Test(expected = DuplicationIdException.class)
    public void 회원가입_중복아이디() throws Exception {
        Member member = new Member();
        member.setId("abc");
        member.setPassword("asdfasdf");
        memberService.save(member);

        Member dupMember = new Member();
        dupMember.setId("abc");
        dupMember.setPassword("111asdfasdf");
        memberService.save(dupMember);
    }

    @Test(expected = AuthFailException.class)
    public void 로그인_아이디_실패(){
        Member member = new Member();
        member.setId("abc");
        member.setPassword("asdfasdf");
        memberService.save(member);

        member.setId("abc111");
        memberService.auth(member);
    }

    @Test(expected = AuthFailException.class)
    public void 로그인_패스워드_실패(){
        Member member = new Member();
        member.setId("abc");
        member.setPassword("asdfasdf");
        memberService.save(member);

        member.setPassword("asdfasdf111");
        memberService.auth(member);
    }
}

