package gmail.yuchisong7.portfolio.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gmail.yuchisong7.portfolio.domain.User;

@Repository
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	//email 중복검사를 위한 메소드
	public List<String> emailcheck(String email){
		List<String> list = 
				sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select email from user")
				.getResultList();
		return list;
	}
	
	//nickname 중복검사를 위한 메소드
	public List<String> nicknamecheck(String nickname){
		List<String> list = 
				sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select nickname from user "
					+ "where nickname = \'" + nickname 
					+ "\'")
				.getResultList();
		return list;
	}
	
	//회원가입을 위한 메소드
	public void join(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	//로그인을 위한 메소드
	//nickname 과 pw를 가지고 로그인
	//nickname을 가지고 모든 정보를 전부 찾아가면 됩니다.
	public List<User> login(String nickname){
		List<User> list = 
				sessionFactory.getCurrentSession()
				.createNativeQuery(
					"select nickname, userpw, email, profile "
					+ "from user "
					+ "where nickname = \'" + nickname 
					+ "\'")
				.getResultList();
		return list;
	}
	
	//회원정보를 수정하는 메소드
	public void update(User user) {
		//다른 SQL 작업과 혼합이 되는 경우 한꺼번에 수행할 때는
		//update 대신에 merge를 사용하며 
		//없으면 저장하고 있으면 수정하고자 하는 경우에는 saveOrUpdate
		sessionFactory.getCurrentSession().merge(user);
	}
	
	//회원정보를 삭제하는 메소드
	public void delete(User user) {
		//이전에 수행 중인 내용을 전부 삭제하고 작업을 수행
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(user);
	}
}

