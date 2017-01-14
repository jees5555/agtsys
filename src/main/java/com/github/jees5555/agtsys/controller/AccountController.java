package com.github.jees5555.agtsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.jees5555.agtsys.domain.Account;
import com.github.jees5555.agtsys.domain.AccountDetail;
import com.github.jees5555.agtsys.domain.SystemConfig;
import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.service.AccountService;
import com.github.jees5555.agtsys.service.SystemConfigService;
import com.github.jees5555.agtsys.service.UserService;
import com.github.jees5555.agtsys.util.MySqlPageTool;

import static com.github.jees5555.agtsys.constants.SystemConfigConstants.*;
import static com.github.jees5555.agtsys.constants.WebContants.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class AccountController extends BaseController {
	@Resource
	private AccountService ac;
	@Resource
	private SystemConfigService scc;
	@Resource
	private UserService us;

	// 加载管理页面
	@RequestMapping("manage")
	public String getAccountManage(Model model) throws Exception {
		// 获取财务类型列表
		SystemConfig systemConfig = new SystemConfig();
		systemConfig.setConfigtype(ACCOUNT_CONFIG_TYPE);
		List<SystemConfig> accountTypeList = scc.getSystemConfigsByConfigType(systemConfig);
		model.addAttribute("accountTypeList", accountTypeList);
		return "accountmanage";
	}

	// 返回用户列表数据
	@RequestMapping("userlist")
	@ResponseBody
	public Object getUserList(User user) throws Exception {
		return us.getPageUsersByUser(user, new MySqlPageTool(1, 12));
	}

	// 财务操作
	@RequestMapping("operateAccount")
	@ResponseBody
	public String doOperateAccount(AccountDetail accountDetail, HttpSession session) throws Exception {
		// 新建账户来查询
		Account account = new Account();
		account.setUserid(accountDetail.getUserid());
		// 查询用户是否有账号
		account = ac.getAccountByUserId(account);
		if (account != null) {
			accountDetail.setDetaildatetime(new Date());
			if (ac.TXoperateAccount(account, accountDetail) == 1) {
				// 记录日志信息
				String operateInfo = accountDetail.getDetailtypename() + ":" + accountDetail.getMemo();
				this.addLogs(this.getSessionUser(session), operateInfo);
				return OPERATE_SUCCESS;
			} else {
				return OPERATE_FAILURE;
			}
		} else {
			return OPERATE_FAILURE;
		}
	}
}
