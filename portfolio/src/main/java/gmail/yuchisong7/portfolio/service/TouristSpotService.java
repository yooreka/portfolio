package gmail.yuchisong7.portfolio.service;

import javax.servlet.http.HttpServletRequest;

public interface TouristSpotService {

	public void tourlist(HttpServletRequest request);
	
	public void tourinsert(HttpServletRequest request);
	
	public void tourdetail(HttpServletRequest request);
}
