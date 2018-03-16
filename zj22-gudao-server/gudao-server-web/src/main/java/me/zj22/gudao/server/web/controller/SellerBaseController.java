package me.zj22.gudao.server.web.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 路径跳转的base类
 */
@Controller
@RequestMapping("/base")
public class SellerBaseController {
	@Resource
	 ServletContext application;
	
	//方法参数folder通过@PathVariable指定其值可以从@RequestMapping的{folder}获取，同理file也一样
	@RequestMapping("/goURL/{folder}/{file}")
	public ModelAndView goURL(@PathVariable String folder, @PathVariable String file) {
		System.out.println("goURL.folder|file===" + folder+"/"+file);
		return new ModelAndView(folder+"/"+file);
//		return "forward:/gudao/"+folder+"/"+file+".ftl";
	}

	@RequestMapping("/goURL/{file}")
	public ModelAndView goURL2(@PathVariable String file) {
		System.out.println("goURL.folder|file===" +"/"+file);
		return new ModelAndView("/"+file);
//		return "forward:/gudao/"+folder+"/"+file+".ftl";
	}

}
