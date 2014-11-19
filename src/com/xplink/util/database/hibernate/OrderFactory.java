/**
 * 
 */
package com.xplink.util.database.hibernate;

import java.lang.reflect.Field;

import org.hibernate.criterion.Order;

/**
 * @author Sorawit Laosinchai
 */
public class OrderFactory {

	private Field field;

	private boolean isDesc;

	/**
	 * @param field
	 * @param isDesc
	 */
	public OrderFactory(Field field) {
		super();
		this.field = field;
		this.isDesc = false;
	}

	/**
	 * @param field
	 * @param isDesc
	 */
	public OrderFactory(Field field, boolean isDesc) {
		super();
		this.field = field;
		this.isDesc = isDesc;
	}

	public Order getOrder() {
		if (isDesc) {
			return Order.desc(field.getName());
		}
		else {
			return Order.asc(field.getName());
		}
	}
}
