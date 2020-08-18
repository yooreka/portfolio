package gmail.yuchisong7.portfolio.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gmail.yuchisong7.portfolio.domain.ShopReview;

@Repository
public class ShopReviewDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void srinsert(ShopReview shopreview) {
		sessionFactory.getCurrentSession().save(shopreview);
	}
	public void srupdate(ShopReview shopreview) {
		sessionFactory.getCurrentSession().merge(shopreview);
	}
	public void srdelete(ShopReview shopreview) {
		sessionFactory.getCurrentSession().delete(shopreview);
	}
}
