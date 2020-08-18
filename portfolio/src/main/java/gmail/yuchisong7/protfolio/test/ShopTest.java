package gmail.yuchisong7.protfolio.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import gmail.yuchisong7.portfolio.dao.ShopReviewDAO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ShopTest {
    @Autowired
    private SessionFactory ssesionFactory;
    
    @Test
    public void sfTest() {
    	System.out.println(ssesionFactory);
    }
   /* 
    @Autowired
    private ShopReviewDAO shopreviewDao;
    
    @Test
    public void shopreviewTest() {
    	
    }*/
}
