package gmail.yuchisong7.portfolio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.yuchisong7.portfolio.dao.TouristSpotDAO;
import gmail.yuchisong7.portfolio.domain.Shop;
import gmail.yuchisong7.portfolio.domain.TouristSpot;
@Service
public class TouristSpotServiceImpl implements TouristSpotService {
	@Autowired
	  private TouristSpotDAO touristspotDao;
	@Override
	@Transactional
	public void tourlist(HttpServletRequest request) {
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
			    List<TouristSpot> list = touristspotDao.tourlist(map);
			    request.setAttribute("tourlist", list);
			    int count = touristspotDao.count(map).intValue();
			    request.setAttribute("count", count);
			}

	@Override
	@Transactional
	public void tourinsert(HttpServletRequest request) {
		//spotid, spotname 등 만들어 데이터 삽입
		int spotid = 1;
		//데이터 개수 가져오기
		int count = touristspotDao.count(new HashMap<String, Object>()).intValue();
		//데이터가 존재하면 가장 큰 spotid의 값에 +1
		if(count != 0) {
			
			spotid = touristspotDao.maxid() + 1;
		}
		String spotname = request.getParameter("spotname");
		String touraddress = request.getParameter("touraddress");
		String tell = request.getParameter("tell");
		String roadaddress = request.getParameter("roadaddress");
		String businesshour = request.getParameter("businesshour");
		String price = request.getParameter("price");
        
		TouristSpot touristspot = new TouristSpot();
		touristspot.setBusinesshour(businesshour);
		touristspot.setPrice(price);
		touristspot.setRoadaddress(roadaddress);
		touristspot.setSpotname(spotname);
		touristspot.setTell(tell);
		touristspot.setTouraddress(touraddress);
		touristspot.setSpotid(spotid);
		touristspotDao.tourinsert(touristspot);
		  
		   request.setAttribute("insert", true);
	}
    @Transactional
	@Override
	public void tourdetail(HttpServletRequest request) {
	
		String spotid = request.getParameter("spotid");
		TouristSpot touristspot= touristspotDao.tourdetail(Integer.parseInt(spotid));
		request.setAttribute("touristspot", touristspot);
	}

}
