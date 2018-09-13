package hello.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.dao.RoomDAO;
import hello.entity.MyUserDetails;
import hello.entity.Room;
import hello.entity.User;
import hello.service.RoomService;
import hello.service.UserService;
import hello.utils.SessionUtils;

/*
 * Chú ý: REST KHÔNG GỬI ContextPath cho client
 */
@Controller
public class MainController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private RoomDAO roomDAO;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		HttpSession session = sessionUtils.getSession(request);
		
		@SuppressWarnings("unchecked")
		Set<Room> userRoomSet = (Set<Room>) session.getAttribute("userRoomSet");
		model.addAttribute("userRoomSet", userRoomSet);
		
		if(userRoomSet.size() == 0) {
			// show some demo rooms
			List<Room> demoRoom = new ArrayList<>();
			demoRoom.add(roomService.findRoomById(123L));
			demoRoom.add(roomService.findRoomById(1234L));
			demoRoom.add(roomService.findRoomById(1000L));
			model.addAttribute("demoRoom", demoRoom);
		}
		
		return "index";
	}
	
	// roomId để kiểu String để tránh ngoại lệ (người dùng có thể nhập 1 chuỗi ở URL thay vì 1 số)
	@RequestMapping("/messages/{roomId}")
	public String chatRoom(HttpServletRequest request, Model model, //
			@PathVariable("roomId") String roomIdString, Principal principal) {

		long roomId;
		try {
			roomId = Long.valueOf(roomIdString);
		} catch (NumberFormatException e) {
			return "room_doesnt_exist";
		}
		
		Room room = roomService.findRoomById(roomId);
		if(room == null) return "room_doesnt_exist";

		List<String> fullnames = userService.getUserFullnameInRoom(roomId);
		
		boolean isBelongToRoom = false;
		HttpSession session = sessionUtils.getSession(request);
		
		// Lấy các room mà người này đang tham gia
		@SuppressWarnings("unchecked")
		Set<Room> userRoomSet = (Set<Room>) session.getAttribute("userRoomSet");
		for(Room r : userRoomSet) {
			if(r.getId() == roomId) {
				isBelongToRoom = true;
				break;
			}
		}
		if(!isBelongToRoom) {
			if(room.getPrivacy().equals(Room.PRIVACY_PUBLIC)) {
				model.addAttribute("titlePage", "Join room");
				model.addAttribute("message", "You haven't participate in this room");
				model.addAttribute("roomId", roomId);
				model.addAttribute("action", "join");
			} else if(room.getPrivacy().equals(Room.PRIVACY_PRIVATE)) {
				model.addAttribute("titlePage", "Join room");
				model.addAttribute("message", "You haven't participate in this room");
				model.addAttribute("roomId", roomId);
				model.addAttribute("action", "request_join");
			} else if(room.getPrivacy().equals(Room.PRIVACY_CLOSED)) {
				model.addAttribute("titlePage", "No permission!");
				model.addAttribute("message", "You don't have permission to join this room!");
				model.addAttribute("action", "no_permission");
			}
			return "no_permission_to_join_room";
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
		
		model.addAttribute("userId", myUserDetails.getId());
		model.addAttribute("username", principal.getName());
		model.addAttribute("fullname", myUserDetails.getFullname());
		model.addAttribute("roomId", roomId);
		model.addAttribute("roomName", room.getName());
		model.addAttribute("isNewbie", false);		// chưa xong phần này
		model.addAttribute("userRoomSet", userRoomSet);
		model.addAttribute("participants", fullnames);
		
		return "chat";
	}
	
	@RequestMapping(value = {"/join/{roomId}"})
    public String joinRoom(HttpServletRequest request, ///
    		@PathVariable("roomId") String roomIdString) {
    	long roomId;
		try {
			roomId = Long.valueOf(roomIdString);
		} catch (NumberFormatException e) {
			return "room_doesnt_exist";
		}

		// Cho phép join tự do
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
		
		if(roomDAO.insertUserToPublicRoom(myUserDetails.getId(), roomId) == true) {
			sessionUtils.updateUserRoomInSession(request.getSession());
		}
		
		return "redirect:/messages/" + roomId;
    }

    @RequestMapping(value = {"/request-join/{roomId}"}, produces = "text/plain;charset=UTF-8")
    @ResponseBody()
    public String requestJoinRoom(HttpServletRequest request, ///
    		@PathVariable("roomId") String roomIdString) {
    	long roomId;
		try {
			roomId = Long.valueOf(roomIdString);
		} catch (NumberFormatException e) {
			return "room_doesnt_exist";
		}
		
		return "Hiện tại chưa có ai có thể approve yêu cầu join room " + roomId + " :v";
    }
    
    @RequestMapping(value = {"/noJS"})
    public String noJS(HttpServletRequest request) {
        return "no_js";
    }
    
    @RequestMapping(value="/demo")
    public String demo(Model model) {
    	// Room r = roomService.findRoomById(123);
    	List<String> participantNames = userService.getUserFullnameInRoom(123);
    	List<Object[]> usernameAndFullnames = userService.getUsernameAndFullname(2);
    	List<Object[]> newestUsernameAndFullnames = userService.get5NewestUsernameAndFullname();
    	List<Object[]> commentsOfUser = userService.getCommentsOfUserInRoom(123);
    	User usernameAndFullnamesJPQL = userService.getUsernameAndFullname_JPQL(2);
    	
    	model.addAttribute("participantNames", participantNames);
    	model.addAttribute("usernameAndFullnames", usernameAndFullnames);
    	model.addAttribute("newestUsernameAndFullnames", newestUsernameAndFullnames);
    	model.addAttribute("commentsOfUser", commentsOfUser);
    	model.addAttribute("usernameAndFullnamesJPQL", usernameAndFullnamesJPQL);
    	
    	return "demo";
    }
}