package com.miguelcarvalho.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;

import com.miguelcarvalho.workshopmongo.domain.Post;

// basicamente o que vai aprecer do user no agregado na parte do post!!
public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String text;
	private Date date;
	
	private AuthorDTO author; // aninhado nos comments que por sua vez estao aninhados no post

	public CommentDTO() {
	}
	
	public CommentDTO(String text,Date date, AuthorDTO author) {
		this.text = text;
		this.date = date;
		this.author = author;
	}

	/*public CommentDTO(String text,Post post) {
		this.text = text;
		date = post.getDate(); //pode ser em data diferente do post
		setAuthor(post.getAuthor()); // ta mal pq quem faz o comentario pode ser qq um
	}*/

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	

	
	
}
