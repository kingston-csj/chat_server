package pers.kinson.im.chat.logic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.listener.annotation.EventHandler;
import pers.kinson.im.chat.logic.login.event.UserLoginEvent;

@Component
public class UserFacade {

	@Autowired
	private UserService userService;

	@EventHandler(value = { EventType.LOGIN })
	public void onUserLogin(UserLoginEvent loginEvent) {
		long userId = loginEvent.getUserId();
		User user = SpringContext.getUserService().getOnlineUser(userId);
		userService.refreshUserProfile(user);
	}

}
