package kr.rang2ne.playground.member;

import kr.rang2ne.playground.member.model.Member;
import kr.rang2ne.playground.member.model.MemberDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

//import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gswon on 15. 12. 2.
 */
@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(
            value = "/members"
            ,method = GET
    )
    public ResponseEntity<List<Member>> getMembers(){
        return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/join",
            method = POST
    )
    public ResponseEntity postMember(
            @RequestBody @Valid MemberDTO.Save saveDTO
    ) throws Exception {
        memberService.save(modelMapper.map(saveDTO, Member.class));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/login",
            method = POST
    )
    public ResponseEntity login(
            @RequestBody @Valid MemberDTO.Login loginDTO,
            HttpSession session
    ) throws Exception {
        Member member = memberService.auth(modelMapper.map(loginDTO, Member.class));
        session.setAttribute("auth", member);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandling(Exception e) {
        return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
