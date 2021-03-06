package com.gene.common.controller;

import org.springframework.stereotype.Controller;

import com.gene.common.utils.ShiroUtils;
import com.gene.owneruser.domain.OwnerUserDO;
import com.gene.system.domain.UserToken;

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