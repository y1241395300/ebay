package Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class demo1 {
@RequestMapping("hello")
public String hello() {
	return "hello word!!!";
}
}
