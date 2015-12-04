package kr.rang2ne.playground.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by gswon on 15. 12. 2.
 */
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @RequestMapping(
            value = "/members"
            ,method = RequestMethod.GET
    )
    public ResponseEntity<List<Member>> getMembers(){
        System.out.println("44444");
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }
}