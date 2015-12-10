package kr.rang2ne.playground.member;

import kr.rang2ne.playground.member.exception.AuthFailException;
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
            @RequestBody @Valid MemberDTO.SaveREQ saveDTO,
            HttpSession session
    ) throws Exception {
        Member memberParam = modelMapper.map(saveDTO, Member.class);
        memberService.save(memberParam);
        Member member = memberService.auth(memberParam);
        session.setAttribute("auth", modelMapper.map(member, MemberDTO.SessionModel.class));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/login",
            method = POST
    )
    public ResponseEntity<MemberDTO.LoginRESP> login(
            @RequestBody @Valid MemberDTO.LoginREQ loginDTO,
            HttpSession session
    ) throws Exception {
        Member memberParam = modelMapper.map(loginDTO, Member.class);
        Member member = memberService.auth(memberParam);
        session.setAttribute("auth", modelMapper.map(member, MemberDTO.SessionModel.class));
        return new ResponseEntity<>(modelMapper.map(member, MemberDTO.LoginRESP.class), HttpStatus.OK);
    }

    @RequestMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        session.removeAttribute("auth");
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(AuthFailException.class)
    public ResponseEntity<Integer> exceptionHandling(AuthFailException e) {
        return new ResponseEntity<>(e.getDetailErrorCode(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> exceptionHandling(Exception e) {
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
