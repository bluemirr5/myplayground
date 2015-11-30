package kr.rang2ne.mobile.contents.delivery;

import kr.rang2ne.mobile.contents.delivery.member.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gswon on 15. 11. 30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    public void memberPrintTest() throws Exception {
//        memberService.printlnFindAll();
//        Member member = new Member();
//        member.setId("myidkkkid");
//        memberService.save(member);
//        Member member2 = new Member();
//        member2.setId("abkkkkab");
//        memberService.save(member2);
//        Member member3 = new Member();
//        member3.setId("abab");
//        memberService.save(member3);

        memberService.setPassword();
//        memberList.forEach(member1 -> System.out.println(member.getId()));

    }
}
