package gmail.yuchisong7.portfolio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import gmail.yuchisong7.portfolio.dao.ShopDAO;
import gmail.yuchisong7.portfolio.domain.Shop;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
	
    private ShopDAO shopDao;
	@Override
	@Transactional
	public void list(HttpServletRequest request) {
		//파라미터 읽기
		String searchtype = request.getParameter("searchtype");
		String value = request.getParameter("value");
		String pageNo = request.getParameter("pageNo");
		//작업을 수행
		int size = 3;
		//시작 위치 번호를 저장할 변수
		//MySQL은 데이터 번호가 0부터 시작
		int start = 0;
		if(pageNo !=null) {
			start = (Integer.parseInt(pageNo) - 1) * size;
		}
		//DAO 메소드의 파라미터를 만들기
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("searchtype", searchtype);
	    map.put("value", value);
	    map.put("start", start);
	    map.put("size", size);
		
		//DAO 메소드를 호출해서 결과를 저장
	    List<Shop> list = shopDao.list(map);
	    request.setAttribute("list", list);
	    int count = shopDao.count(map).intValue();
	    request.setAttribute("count", count);
	}

	@Override
	@Transactional
	public void detail(HttpServletRequest request) {
		String shopid = request.getParameter("shopid");
		Shop shop = shopDao.detail(Integer.parseInt(shopid));
		request.setAttribute("shop", shop);
	}


             
	@Override
	@Transactional
	public void insert(HttpServletRequest request) {
		//shopid, shopname 등 만들어서 데이터 삽입
				int shopid = 1;
				//데이터 개수 가져오기
				int count = shopDao.count(new HashMap<String, Object>()).intValue();
				//데이터가 존재하면 가장 큰 shopid의 값에 +1
				if(count != 0) {
					
					shopid = shopDao.maxid() + 1;
				}
				String shopname = request.getParameter("shopname");
				String businesshour = request.getParameter("businesshour");
				String mobile =  request.getParameter("mobile");
				String roadaddress =  request.getParameter("roadaddress");
				String address =  request.getParameter("address");
				   Shop shop = new Shop();
				  shop.setAddress(address);
				  shop.setBusinesshour(businesshour);
				  shop.setMobile(mobile);
				  shop.setRoadaddress(roadaddress);
				  shop.setShopid(shopid);
				  shop.setShopname(shopname);
				  
				   shopDao.insert(shop);
				  
				   request.setAttribute("insert", true);
	}
   
}

