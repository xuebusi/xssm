package com.xuebusi.xssm.common;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;

/**
 * JSR303 Validator(Hibernate Validator)工具类.
 * 
 * ConstraintViolation中包含propertyPath, message 和invalidValue等信息.
 */
public class BeanValidators {

	/**
	 * 服务器端参数校验
	 * @param validator
	 * @param object
	 * @throws ConstraintViolationException
	 */
	public static void validate(Validator validator, Object object) throws ConstraintViolationException {
		Set constraintViolations = validator.validate(object);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	/**
	 * 校验信息封装成list
	 * @param e
	 * @param separator
	 * @return
	 */
	public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
		return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);
	}

	/**
	 * 校验信息封装成list
	 * @param constraintViolations
	 * @param separator
	 * @return
	 */
	public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,
															   String separator) {
		List<String> errorMessages = new ArrayList<>();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
		}
		return errorMessages;
	}

	/**
	 * 校验信息封装成Map
	 * @param e
	 * @return
	 */
	public static Map<String, Object> extractPropertyAndMessageAsMap(ConstraintViolationException e) {
		return extractPropertyAndMessageAsMap(e.getConstraintViolations());
	}

	/**
	 * 校验信息封装成Map
	 * @param constraintViolations
	 * @return
	 */
	public static Map<String, Object> extractPropertyAndMessageAsMap(Set<? extends ConstraintViolation> constraintViolations) {
		Map<String, Object> errorMessages = new HashMap<>();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return errorMessages;
	}

	public static String extractPropertyAndMessageAsString(ConstraintViolationException e, String separator) {
		return extractPropertyAndMessageAsString(e.getConstraintViolations(), separator);
	}

	/**
	 * 辅助方法, 转换Set<ConstraintViolation>为List<message>
	 */
	public static String extractPropertyAndMessageAsString(Set<? extends ConstraintViolation> constraintViolations, String separator) {
		StringBuilder builder = new StringBuilder();
		for (ConstraintViolation violation : constraintViolations) {
			builder.append(violation.getPropertyPath()).append(separator);
		}
		return builder.toString();
	}
}