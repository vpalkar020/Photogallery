package org.studyeasy.hibernate.entity;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="files")
@Table(name="files")
public class Files {

	@Id
	@Column(name="id")
	int id;
	
	@Column(name="filename")
	String filename;
	
	@Column(name="caption")
	String caption;
	
	@Column(name="label")
	String label;
	public Files() {}
	
	public Files(String filename) {
		this.filename=filename;
	}
	public Files(int id, String caption, String label) {
		this.id = id;
		this.caption = caption;
		this.label = label;
	}

	public Files(int id,String filename,String caption,String label) {
		this.filename=filename;
		this.id=id;
		this.caption=caption;
		this.label=label;	
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return "Files [id=" + id + ", filename=" + filename + ", label=" + label + ", caption=" + caption + "]";
	}	
}