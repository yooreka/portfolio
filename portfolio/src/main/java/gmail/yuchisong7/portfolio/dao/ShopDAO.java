package gmail.yuchisong7.portfolio.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gmail.yuchisong7.portfolio.domain.Shop;

@Repository
public class ShopDAO {
    @Autowired
    private SessionFactory sessionFactory;
    
  //검색항목과 검색어 그리고 페이지 번호를 이용해서 해당하는 데이터를 목록으로 리턴하느 메소드
  //페이징을 할 때 오라클은 시작번호와 종료번호를 알아야하지만 MySQL은 시작번호와 데이터 개수를 알아야 함.
  //오라클은 1부터 시작하지만 MySQl은 0부터 시작
  //검색항목과 검색어 그리고 시작번호 페이지당 데이터 개수를 하나의 Map으로 묶어서 사용
    public List<Shop> list(Map<String, Object> map){
    	//검색항목을 저장할 list
    	List<Shop> list = new ArrayList<Shop>();
    	
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
    	//shopname에서 검색
    	}
    	if(searchtype == null) {
			list = sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select * from shop limit " 
				+ start + "," + size ).getResultList();
		}else if(searchtype.equals("shopname")) {
			list = sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select * from shop where "
					+ "lower(shopname) like \'" 
							+ value + "\' limit " + 
					start + "," + size).getResultList();}
    	return list;
    }
  	//검색 결과에  데이터 개수를 리턴하는 메소드
	//하이버네이트에서는 정수를 리턴할 때는 BigInteger로 리턴됨
	public BigInteger count(Map<String, Object> map) {
		//파라미터 읽기 - ServiceImpl에서 만들어서 넘겨주어야 함.
    	String searchtype = (String)map.get("searchtype");
    	String value = (String)map.get("value");
        
    	List<BigInteger> list = null;
    	if(value != null) {
    	value = "%" + value.toLowerCase() + "%";
    	}
    	if(searchtype == null || searchtype.equals("")) {
			list = sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select count(*) from shop")
				.getResultList();
		}else if(searchtype.equals("shopname")) {
			list = sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select count(*) from shop " + 
					"where lower(shopname) like \'" + 
					value + "\'")
						.getResultList();
			}
		return list.get(0);
	}
  //shopid를 가지고 데이터 1개를 찾아오는 메소드
	public Shop detail(int shopid) {
		Shop shop = sessionFactory.getCurrentSession().get(Shop.class, shopid);
		return shop;
	}
	//가장 큰 ShopId를 찾아오는 메소드
	public int maxid() {
		List<Integer> list = sessionFactory.getCurrentSession().createNativeQuery("select max(shopid) from shop").getResultList();
		return list.get(0);
		
	}
	//데이터를 삽입하는 메소드
	public void insert(Shop shop) {
		sessionFactory.getCurrentSession().save(shop);
	}
}
