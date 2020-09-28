package com.vj.learn.restfulwebservice.post;

import java.util.Date;

public class Posts {

	private Integer postId;
	private Date time;
	private String header;
	private String content;

	public Posts(Integer postId, Date time, String header, String content) {
		super();
		this.postId = postId;
		this.time = time;
		this.header = header;
		this.content = content;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", time=" + time + ", header=" + header + ", content=" + content + "]";
	}

}
