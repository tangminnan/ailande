package com.yanke.common.controller;

import org.springframework.stereotype.Controller;

import com.yanke.common.utils.ShiroUtils;
import com.yanke.owneruser.domain.OwnerUserDO;
import com.yanke.system.domain.UserToken;

@Controller
public class BaseController {
	public OwnerUserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getChectorId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
	/*public Long getforIds() {
		return getUser().getUserId();
	}*/
}