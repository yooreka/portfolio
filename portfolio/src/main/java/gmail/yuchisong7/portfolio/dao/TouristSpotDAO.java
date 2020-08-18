package gmail.yuchisong7.portfolio.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gmail.yuchisong7.portfolio.domain.Shop;
import gmail.yuchisong7.portfolio.domain.TouristSpot;

@Repository
public class TouristSpotDAO {
    @Autowired
	private SessionFactory sessionFactory;
    public List<TouristSpot> tourlist(Map<String, Object> map){
    	//검색항목을 저장할 list
    	List<TouristSpot> tourlist = new ArrayList<TouristSpot>();
    	
    	//검색항목  -searchtype, 검색어 - value
    	//시작번호 - start 페이지 당 데이터 개수 - size
    	
    	//파라미터 읽기 - ServiceImpl에서 만들어서 넘겨주어야 함.
    	String searchtype = (String)map.get("searchtype");
    	String value = (String)map.get("value");
    	int start = (Integer)map.get("start");
    	int size = (Integer)map.get("size");
    	
    	if(value != null) {
    	//like 검색을 하고자 하는 경우
    	//대소문자 구분하지 않고 검색하기 위해서 toLowerCase 사용
    	value = "%" + value.toLowerCase() + "%";
    	//검색 항목이 없는경우
    	//spotname에서 검색
    	}
    	if(searchtype == null) {
			tourlist = sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select * from touristspot limit " 
				+ start + "," + size ).getResultList();
		}else if(searchtype.equals("spotname")) {
			tourlist = sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select * from touristspot where "
					+ "lower(spotname) like \'" 
							+ value + "\' limit " + 
					start + "," + size).getResultList();}
    	return tourlist;
    }
  	//검색 결과에  데이터 개수를 리턴하는 메소드
	//하이버네이트에서는 정수를 리턴할 때는 BigInteger로 리턴됨
	public BigInteger count(Map<String, Object> map) {
		//파라미터 읽기 - ServiceImpl에서 만들어서 넘겨주어야 함.
    	String searchtype = (String)map.get("searchtype");
    	String value = (String)map.get("value");
        
    	List<BigInteger> tourlist = null;
    	if(value != null) {
    	value = "%" + value.toLowerCase() + "%";
    	}
    	if(searchtype == null) {
			tourlist = sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select count(*) from touristspot")
				.getResultList();
		}else if(searchtype.equals("spotname")) {
			tourlist = sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select count(*) from touristspot " + 
					"where lower(spotname) like \'" + 
					value + "\'")
						.getResultList();
			}
		return tourlist.get(0);
	}
  //shopid를 가지고 데이터 1개를 찾아오는 메소드
	public TouristSpot tourdetail(int spotid) {
		TouristSpot touristspot = sessionFactory.getCurrentSession().get(TouristSpot.class, spotid);
		return touristspot;
	}
	//가장 큰 ShopId를 찾아오는 메소드
	public int maxid() {
		List<Integer> tourlist = sessionFactory.getCurrentSession().createNativeQuery("select max(spotid) from touristspot").getResultList();
		return tourlist.get(0);
		
	}
	//데이터를 삽입하는 메소드
	public void tourinsert(TouristSpot touristspot) {
		sessionFactory.getCurrentSession().save(touristspot);
	}
}
