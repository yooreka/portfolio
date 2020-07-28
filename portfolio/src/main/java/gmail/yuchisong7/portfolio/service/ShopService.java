package gmail.yuchisong7.portfolio.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ShopService {
     //검색어를 가지고 검색하는 메소드
	public void list(HttpServletRequest request);
	//Shopid를 이용해서 하나의 데이터를 가져오는 메소드
	public void detail(HttpServletRequest request);
	//데이터를 삽입하는 메소드
	//데이터를 업로드 할 때는 일반 HttpServletRequest로 못함
	public void insert(HttpServletRequest request);
}
