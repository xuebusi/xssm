package com.xuebusi.xssm.controller;

import com.xuebusi.xssm.common.BeanValidators;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseController {
	
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 */
	public String beanValidator(Object object) {
		try {
			BeanValidators.validate(validator, object);
		} catch (ConstraintViolationException ex) {
			String message = BeanValidators.extractPropertyAndMessageAsString(ex, ",");
			return message;
		}
		return null;
	}
}
