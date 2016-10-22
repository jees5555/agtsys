package jym.agtsys.controller;

import static jym.agtsys.constants.WebContants.OPERATE_FAILURE;
import static jym.agtsys.constants.WebContants.OPERATE_SUCCESS;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jym.agtsys.domain.Role;
import jym.agtsys.domain.SystemConfig;
import jym.agtsys.service.SystemConfigService;

@Controller
@RequestMapping("systemconfig")
public class SystemConfigController {
    @Resource
	private SystemConfigService scs;
    
    //返回系统设置列表页面，需要判断类型
    @RequestMapping(value={"manage/{configtype}"},method=RequestMethod.GET)
    public String getSystemConfigManage(@PathVariable Integer configtype,Model model){
        model.addAttribute("configtype", configtype);
    	return "systemconfigmanage";
    }
	
    //返回系统设置列表数据
    @RequestMapping(value={"list/{configtype}"})
    @ResponseBody
    public Object getSystemConfigList(@PathVariable Integer configtype) throws Exception{
    	SystemConfig sc =new SystemConfig();
    	sc.setConfigtype(configtype);
    	return scs.getSystemConfigByConfigType(sc);
    }
    //返回添加配置类型页面
    @RequestMapping(value={"add/{configtype}"},method=RequestMethod.GET)
    public String addSystemConfig (@PathVariable Integer configtype,Model model){
    	model.addAttribute("configtype", configtype);
    	return "addsystemconfig";
    }
  //检查配置类型是否存在
    @RequestMapping(value={"configtypenameCheck"},method=RequestMethod.POST)
    @ResponseBody
    public String configtypenameCheck(SystemConfig systemConfig) throws Exception{
    	List <SystemConfig> systemConfigs=scs.getSystemConfigByConfigType(systemConfig);
    	if(systemConfigs.size()==0){
    		return OPERATE_SUCCESS;
    	}else{
    	    return OPERATE_FAILURE;
    	}
    }
  //添加配置类型
    @RequestMapping(value={"add"},method=RequestMethod.POST)
    @ResponseBody
    public String doAddSystemConfig(SystemConfig systemConfig) throws Exception{
    	if(scs.addSystemConfig(systemConfig)==1){
    		return OPERATE_SUCCESS;
    	}else{
    	    return OPERATE_FAILURE;
    	}
    }
    //返回修改配置类型页面
    @RequestMapping(value={"update/{id}"},method=RequestMethod.GET)
    public String updateSystemConfig (@PathVariable Long id,Model model) throws Exception{
    	SystemConfig systemConfig =new SystemConfig();
    	systemConfig.setId(id);
    	systemConfig=scs.getSystemConfigById(systemConfig);
    	model.addAttribute("systemconfig", systemConfig);
    	return "updatesystemconfig";
    }
  //修改配置类型
    @RequestMapping(value={"update"},method=RequestMethod.POST)
    @ResponseBody
    public String doUpdateSystemConfig(SystemConfig systemConfig) throws Exception{
    	if(scs.updateSystemConfig(systemConfig)==1){
    		return OPERATE_SUCCESS;
    	}else{
    	    return OPERATE_FAILURE;
    	}
    }
   //删除配置类型
    @RequestMapping(value={"delete"},method=RequestMethod.POST)
    @ResponseBody
    public String doSystemConfig (SystemConfig systemConfig) throws Exception{
	     if(scs.deleteSystemConfig(systemConfig)==1){
		     return OPERATE_SUCCESS;
	     }else{
	         return OPERATE_FAILURE;
	     }
    }
}
