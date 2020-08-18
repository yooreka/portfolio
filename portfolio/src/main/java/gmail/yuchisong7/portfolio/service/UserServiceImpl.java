package gmail.yuchisong7.portfolio.service;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import gmail.yuchisong7.portfolio.dao.UserDAO;
import gmail.yuchisong7.portfolio.domain.User;
import gmail.yuchisong7.portfolio.util.CryptoUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;

	// 하이버네이트는 트랜젝션션을 사용하지 않으면 예외가 발
	@Override
	@Transactional
	public void join(MultipartHttpServletRequest request) {
		// 파라미터 읽기
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String userpw = request.getParameter("userpw");
		MultipartFile image = request.getFile("profile");

		System.out.println("email:" + email);
		System.out.println("userpw:" + userpw);
		System.out.println("nickname:" + nickname);

		// 결과를 저장할 Map을 생성
		Map<String, Object> map = new HashMap<String, Object>();
		// result는 회원가잆성공여부
		// emailcheck에는 이메일중복 검사 통과 여부, nicknamecheck에는 닉네임 중복 검사 통과여부
		map.put("result", false);
		map.put("emailcheck", false);
		map.put("nicknamecheck", true);
		System.out.println(map);
		String key = "yu7734@naver.com";
		// email 중복검사
		// 복호화가 가능한 암호화로 되어 있음
		List<String> emaillist = userDao.emailcheck(email);
		for (String temp : emaillist) {
			try { // 복호화 해서 비교
				if (CryptoUtil.decryptAES256(temp, key).equals(email));
				{
					map.put("emailcheck", false);
					request.setAttribute("result", map);
					return;
				}
			} catch (Exception e) { e.printStackTrace();
		   }
		}
		
	
		// nickname중복검사
		List<String> nicknamelist = userDao.nicknamecheck(nickname);
		if (nicknamelist != null && nicknamelist.size() > 0) {
			map.put("nickname", false);
			request.setAttribute("reuslt", map);
			return;
		}
		// 파일 업로드
		String profile = "default.jpg";
		// 이미지가 존재하면 업로드
		if (image != null && image.isEmpty() == false) {
			// 업로드할 디렉토리 경로 생성
			String filePath = request.getRealPath("/profile");
			// 파일 이름생성
			profile = UUID.randomUUID() + image.getOriginalFilename();
			// 업로드할 파일 경로를 생성
			filePath = filePath + "/" + profile;
			// 파일 업로드
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(filePath);
				fos.write(image.getBytes());
			} catch (Exception e) {
				System.out.println("파일 업로드 실패 :" + e.getMessage());
			}
		}
		// Dao의 파라미터 만들기
		User user = new User();
		try {
			user.setEmail(CryptoUtil.encryptAES256(email, key));
		} catch (Exception e) {
		}
		user.setNickname(nickname);
		user.setUserpw(BCrypt.hashpw(userpw, BCrypt.gensalt()));
		user.setProfile(profile);

		// 회원가입 메소드 호출
		userDao.join(user);
		map.put("result", true);
		request.setAttribute("result", map);
		// map에서 result가 회원가입 성공여부

	}

	@Override
	@Transactional
	public void login(HttpServletRequest request) {
		// 파라미터 읽기
		String email = request.getParameter("email");
		String userpw = request.getParameter("userpw");
		System.out.println("email:" + email);
		System.out.println("userpw:" + userpw);
		// 로그인 성공 여부 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		// nickname을 가지고 데이터를 찾아오기
		List<User> list = userDao.login(email);
		if (list == null || list.size() < 1) {
			request.setAttribute("result", map);
			return;
		}
		// 찾아온 데이터와 비밀번호를 비교
		for (User user : list) {
			if (BCrypt.checkpw(userpw, user.getUserpw())) {
				map.put("email", email);
				map.put("profile", user.getProfile());
				try {
					map.put("email", CryptoUtil.decryptAES256(user.getEmail(), "yu7734@naver.com"));
				} catch (Exception e) {e.getMessage();
				}
			}
		}
	}

	@Override
	public void update(MultipartHttpServletRequest request) {// 파라미터 읽기
		String nickname = request.getParameter("nickname");
		String userpw = request.getParameter("userpw");
		// 이미지는 반드시 입력되는 항목이 아니라면
		// 이전값을 파라미터로 보내고 새로운 값이 null이 아니라면 새로운 값으로 대체
		String profile = request.getParameter("oldprofile");
		// email 찾아오기
		List<User> list = userDao.login(nickname);
		String email = list.get(0).getEmail();

		// 이미지 파일 업로드
		MultipartFile image = request.getFile("profile");
		if (image != null && image.isEmpty() == false) {
			// 업로드할 파일 경로 생성
			String filePath = request.getRealPath("/profile");
			profile = UUID.randomUUID() + image.getOriginalFilename();
			// 파일업로
			filePath = filePath + "/" + profile;
			try {
				FileOutputStream fos = new FileOutputStream(filePath);
				fos.write(image.getBytes());
			} catch (Exception e) {
				System.out.println("파일 업로드 예외" + e.getMessage());
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);

		// DAO의 파라미터 만들기
		User user = new User();
		user.setEmail(email);
		user.setUserpw(userpw);
		user.setNickname(nickname);
		user.setProfile(profile);

		userDao.update(user);
		map.put("reuslt", true);
		request.setAttribute("result", map);
	}

	@Override
	public void delete(HttpServletRequest request) {
		String nickname = request.getParameter("nickname");
		List<User> list = userDao.login(nickname);
		String email = list.get(0).getEmail();
		User user = new User();
		user.setEmail(email);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		userDao.delete(user);
		map.put("result", true);
		request.setAttribute("result", map);
	}

}
