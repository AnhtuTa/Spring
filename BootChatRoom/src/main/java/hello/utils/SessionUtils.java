package hello.utils;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import hello.entity.MyUserDetails;
import hello.entity.User;

@Component
@Transactional
public class SessionUtils {
	@Autowired
	private EntityManager entityManager;
	
	public HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userRoomSet") == null) {
			session = updateUserRoomInSession(session);
		}
		
		return session;
	}
	
	public HttpSession updateUserRoomInSession(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
		
		User user = entityManager.find(User.class, myUserDetails.getId());
		session.setAttribute("userRoomSet", user.getRooms());
		
		return session;
	}
	
//	public HttpSession getSession(HttpServletRequest request, long userId) {
//		HttpSession session = request.getSession();
//		if(session.getAttribute("userRoomList") == null) {
//			
//			User user = entityManager.find(User.class, userId);
//			session.setAttribute("userRoomList", user.getRooms());
//		}
//		
//		return session;
//	}
}
