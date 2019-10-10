package com.yanke.common.controller;

import org.springframework.stereotype.Controller;

import com.yanke.common.utils.ShiroUtils;
import com.yanke.system.domain.UserDO;
import com.yanke.system.domain.UserToken;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}