package gmail.yuchisong7.portfolio.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.yuchisong7.portfolio.dao.ShopReviewDAO;
import gmail.yuchisong7.portfolio.domain.ShopReview;

@Service
public class ShopReviewServiceImpl implements ShopReviewService {
	@Autowired
	private ShopReviewDAO shopreviewDao;

	@Transactional
	@Override
	public void srinsert(HttpServletRequest request) {

		String nickname = request.getParameter("nickname");
		int shopid = Integer.parseInt(request.getParameter("shopid")); 
		String shopmessage = request.getParameter("shopmessage");
		Timestamp shopcommentdate = new Timestamp(System.currentTimeMillis());
		
		
		ShopReview shopreview = new ShopReview();
		shopreview.setNickname(nickname);
		shopreview.setShopid(shopid);
		shopreview.setShopcommentdate(shopcommentdate);
		shopreview.setShopmessage(shopmessage);
		
		shopreviewDao.srinsert(shopreview);
		request.setAttribute("srinsert", true);
	}

	@Override
	public void srupdate(HttpServletRequest request) {

	}

	@Override
	public void srdelete(HttpServletRequest request) {

	}

}
