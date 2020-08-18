package gmail.yuchisong7.portfolio.service;

import javax.servlet.http.HttpServletRequest;

public interface ShopReviewService {
	public void srinsert(HttpServletRequest request);

	public void srupdate(HttpServletRequest request);

	public void srdelete(HttpServletRequest request);
}
