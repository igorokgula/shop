package com.springapp.mvc;

import com.shop.storage.repository.UserRepository;
import com.shop.storage.repository.implementation.JPAOrderItemRepository;
import com.shop.storage.repository.implementation.JPAProductRepository;
import com.shop.storage.repository.implementation.JPAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	@Autowired
	private JPAProductRepository productRepository;

	@Autowired
	private JPAOrderItemRepository jpaOrderItemRepository;

	@Autowired
	private Comp comp;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		System.out.println("cdfvfd");
		return "hello";
	}

	@RequestMapping(value = "hello", method = RequestMethod.POST)
	public String hello(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		System.out.println("================================================" + comp.getSm());
		System.out.println(jpaOrderItemRepository.run());
		return "hello";
	}
}