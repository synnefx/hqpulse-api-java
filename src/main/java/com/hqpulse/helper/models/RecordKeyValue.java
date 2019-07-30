package com.hqpulse.helper.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordKeyValue<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5361718626013673273L;

	private Long id;

	private String sourceId;
	
	private String status;

	private List<String> messages;
	
	private T item;
	
	public RecordKeyValue(Long id, String status){
		this.id = id;
		this.status = status;
	}

	public RecordKeyValue(String sourceId, String status){
		this.sourceId = sourceId;
		this.status = status;
	}
	
	public RecordKeyValue(Long id, String status, T item){
		this.id = id;
		this.status = status;
		this.item = item;
	}
	

	public RecordKeyValue(Long id, String status, List<String> msgs, T item){
		this.id = id;
		this.status = status;
		this.messages = msgs;
		this.item = item;
	}
	
	public RecordKeyValue(Long id, String status, String msg, T item){
		this.id = id;
		this.status = status;
		this.messages = new ArrayList<>(Arrays.asList(msg));
		this.setItem(item);
	}
	
	public RecordKeyValue(Long id, List<String> msgs){
		this.id = id;
		this.messages = msgs;
	}
	
	public RecordKeyValue(Long id, String status, String msg) {
		this.id = id;
		this.status = status;
		this.messages = new ArrayList<>(Arrays.asList(msg));
	}

	public RecordKeyValue(String sourceId, String status, String msg) {
		this.sourceId = sourceId;
		this.status = status;
		this.messages = new ArrayList<>(Arrays.asList(msg));
	}

	public RecordKeyValue(Long id, String status,
                          List<String> msgs) {
		this.id = id;
		this.status = status;
		this.messages = msgs;
	}


	public RecordKeyValue(String sourceId, String status,
                          List<String> msgs) {
		this.sourceId = sourceId;
		this.status = status;
		this.messages = msgs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public String toString() {
		return "RecordKeyValue{" +
				"status='" + status + '\'' +
				", messages=" + messages +
				'}';
	}
}
