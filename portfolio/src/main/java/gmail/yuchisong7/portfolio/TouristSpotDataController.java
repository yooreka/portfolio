package gmail.yuchisong7.portfolio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmail.yuchisong7.portfolio.domain.Shop;
import gmail.yuchisong7.portfolio.domain.TouristSpot;
import gmail.yuchisong7.portfolio.service.ShopService;
import gmail.yuchisong7.portfolio.service.TouristSpotService;

@RestController
public class TouristSpotDataController {
	 @Autowired
	    private TouristSpotService touristspotservice;
	    
	    //검색해서 데이터를 전송하는 요청을 생성
	    @RequestMapping(value="tourlist")
	    public Map<String, Object> tourlist(HttpServletRequest request){
	    	//서비스 메소드를 호출해서 결과를 가져옴
	    	touristspotservice.tourlist(request);
	    	List<Shop> list = (List<Shop>)request.getAttribute("tourlist");
	    	int count = (Integer)request.getAttribute("count");
	    	
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("count", count);
	    	map.put("tourlist", list);
	    	return map;
	    	
	    }
	    @RequestMapping(value="tourdetail")
	    public Map<String, Object>detail(HttpServletRequest request){
	    	
	    	touristspotservice.tourdetail(request);
	    	TouristSpot touristspot= (TouristSpot)request.getAttribute("touristspot");
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("touristspot", touristspot);
	    	return map;
	    }
	    @RequestMapping(value="tourinsert", method=RequestMethod.POST)
	    public Map<String, Object>touristinsert(HttpServletRequest request){
	    	
	    	touristspotservice.tourinsert(request);
	        Boolean result = (Boolean)request.getAttribute("insert");
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	//삽입에 성공하면 result는 true 그렇지 않으면 false
	    	if(result != null) 
	    		map.put("result", result);
	    	else
	    		map.put("result", false);
	    	return map;
	    }
	}

