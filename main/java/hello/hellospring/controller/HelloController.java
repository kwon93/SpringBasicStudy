package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") //hello.html 매핑
    public String hello(Model model){
        //모델에 hello! 라는 value를 추가해줌.
        model.addAttribute("data","hello!");
        return "hello"; //hello라는 html 파일을 찾아감.
    }

    //템플릿 엔진 방식.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
            model.addAttribute("name",name);
            return "hello-template";
    }


    //API방식.
    @GetMapping("hello-string")
    @ResponseBody //HTTP 바디에 내가 직접 name을 넣어주겠다라는 뜻.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name ;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello h = new Hello();
        h.setName(name);
        return h; //Spring은 객체를 리턴할때 JSON으로 반환하는게 기본 방식이다.
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
