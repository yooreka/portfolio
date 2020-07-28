package gmail.yuchisong7.portfolio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import gmail.yuchisong7.portfolio.domain.Shop;
import gmail.yuchisong7.portfolio.service.ShopService;

@RestController
public class ShopDataController {
    @Autowired
    private ShopService shopService;
    
    //검색해서 데이터를 전송하는 요청을 생성
    @RequestMapping(value="list")
    public Map<String, Object> list(HttpServletRequest request){
    	//서비스 메소드를 호출해서 결과를 가져옴
    	shopService.list(request);
    	List<Shop> list = (List<Shop>)request.getAttribute("list");
    	int count = (Integer)request.getAttribute("count");
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("count", count);
    	map.put("list", list);
    	return map;
    	
    }
    @RequestMapping(value="detail")
    public Map<String, Object>detail(HttpServletRequest request){
    	
    	shopService.detail(request);
    	Shop shop = (Shop)request.getAttribute("shop");
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("shop", shop);
    	return map;
    }
    @RequestMapping(value="insert", method=RequestMethod.POST)
    public Map<String, Object>insert(HttpServletRequest request){
    	
    	shopService.insert(request);
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
