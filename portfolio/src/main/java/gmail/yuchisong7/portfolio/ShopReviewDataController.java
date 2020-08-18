package gmail.yuchisong7.portfolio;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmail.yuchisong7.portfolio.service.ShopReviewService;

@RestController
public class ShopReviewDataController {

	@Autowired
	private ShopReviewService shopreviewService;
	
	@RequestMapping(value="srinsert", method=RequestMethod.POST)
	 public Map<String, Object>srinsert(HttpServletRequest request){
	   
	  shopreviewService.srinsert(request);
	  Boolean result = (Boolean)request.getAttribute("srinsert");
	  Map<String, Object>map = new HashMap<String, Object>();
	    
	  	if(result != null) {
	  		map.put("result", result);
	  	}else {
	  		map.put("result", false);
	  	}
	  
		return map;
	}
	
}
