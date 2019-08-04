package com.freshandfresh.api.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_book_id")
	private OrderBook orderBook;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item__id")
	private Item item;

	private double price;

	private int noOfUnit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OrderBook getOrderBook() {
		return orderBook;
	}

	public void setOrderBook(OrderBook orderBook) {
		this.orderBook = orderBook;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNoOfUnit() {
		return noOfUnit;
	}

	public void setNoOfUnit(int noOfUnit) {
		this.noOfUnit = noOfUnit;
	}
}
