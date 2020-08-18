package gmail.yuchisong7.portfolio;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import gmail.yuchisong7.portfolio.service.UserService;

@RestController
public class UserDataController {
 @Autowired
 private UserService userService;
 
 @RequestMapping(value="join", method=RequestMethod.POST)
 public Map<String, Object> join(MultipartHttpServletRequest request){
	 userService.join(request);
	 Map<String, Object>map = (Map<String, Object>) request.getAttribute("result");
	 return map;
	
 }
 @RequestMapping(value="login", method=RequestMethod.POST)
 public Map<String, Object> loign(HttpServletRequest request){
	 userService.login(request);
	 Map<String, Object>map = (Map<String, Object>) request.getAttribute("result");
	 return map;
 }
 @RequestMapping(value="update", method=RequestMethod.POST)
 public Map<String, Object> update(MultipartHttpServletRequest request){
	 userService.update(request);
	 Map<String, Object>map = (Map<String, Object>) request.getAttribute("result");
	 return map;
 }
 @RequestMapping(value="delete", method=RequestMethod.POST)
 public Map<String, Object> delete(MultipartHttpServletRequest request){
	 userService.delete(request);
	 Map<String, Object>map = (Map<String, Object>) request.getAttribute("result");
	 return map;
 }
}
