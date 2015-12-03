package kr.rang2ne.mobile.contents.delivery.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gswon on 15. 12. 2.
 */
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @RequestMapping(
            name = "/members"
            ,method = RequestMethod.GET
    )
    public ResponseEntity getMembers(){
        return new ResponseEntity(memberService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(
            name = "/hello"
            ,method = RequestMethod.GET
    )
    public String test124(){
        return "hello";
    }
}
