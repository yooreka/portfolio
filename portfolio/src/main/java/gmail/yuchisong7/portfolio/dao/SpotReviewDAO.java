package gmail.yuchisong7.portfolio.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gmail.yuchisong7.portfolio.domain.SpotReview;



@Repository
public class SpotReviewDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	
	
	public void sinsert(SpotReview spotreview) {
		sessionFactory.getCurrentSession().save(spotreview);
	}
	public void supdate(SpotReview spotreview) {
		sessionFactory.getCurrentSession().merge(spotreview);
	}
	public void sdelete(SpotReview spotreview) {
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(spotreview);
	}
}
