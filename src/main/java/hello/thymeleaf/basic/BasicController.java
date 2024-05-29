package hello.thymeleaf.basic;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "<b>Hello Spring</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userb", 20);

        List<User> list = new ArrayList<>(); // list 생성 후 userA, userB 추가 arraylist 사용
        list.add(userA); //list에 userA 추가
        list.add(userB); //list에 userB 추가

        Map<String,User> map = new HashMap<>(); //map 생성 후 userA, userB 추가  hashmap 사용
        map.put("userA", userA); //map에 userA 추가
        map.put("userB", userB); //map에 userB 추가

        model.addAttribute("user", userA); //userA를 user로 추가
        model.addAttribute("users", list); //list를 users로 추가
        model.addAttribute("userMap", map); //map을 userMap으로 추가

     return "basic/variable";
    }


    @Data
    static class User{
        private String username;
        private int age;

        private User(String username, int age){
            this.username = username;
            this.age = age;
        }
    }

    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request,
                               HttpServletRequest response, HttpSession session){
        session.setAttribute("sessionData", "Hello Session"); //sessionData에 Hello Session 추가
        model.addAttribute("request", request); //스프링 3.0 버전 이상 부터는 model에 직접 추가해야 에러 발생하지 않는다.
        model.addAttribute("response", response); // 스프링 3.0 버전 이상이어서 model에 직접 추가해야 에러 발생하지 않는다.
        model.addAttribute("servletContext", request.getServletContext()); // 스픙링 3.0 버전이상이어서 model에 직접 추가해야 에러 발생하지 않는다.
        return "basic/basic-objects";
    }

    @Component("helloBean") //helloBean으로 빈 등록
    static class HelloBean{ //HelloBean 클래스
        public String hello(String data){ //hello 메소드
            return "Hello " + data; //Hello data 반환
        }
    }

    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now()); //현재 시간을 localDateTime으로 추가
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1"); //param1에 data1 추가
        model.addAttribute("param2", "data2"); //param2에 data2 추가
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null); //nullData에 null 추가
        model.addAttribute("data", "Spring!"); //data에 Spring! 추가
        return "basic/operation";
    }
}
