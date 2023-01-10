package es.urjc.model;


import io.vertx.codegen.annotations.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EoloPlant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String city;
	private String planning;

	private Integer progress;

	private Boolean completed;

	public EoloPlant(Long id ,String city, String planning, int progress, boolean completed) {
		this.id = id;
		this.city = city;
		this.planning = planning;
		this.progress = progress;
		this.completed = completed;
	}

	public EoloPlant(String city) {
		this.city = city;
	}

	public EoloPlant() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPlanning() {
		return planning;
	}

	public void setPlanning(String planning) {
		this.planning = planning;
	}


	public String toString() {
		return "EoloPlant{" +
				"id=" + id +
				", city='" + city + '\'' +
				", planning='" + planning + '\'' +
				", progress='" + progress + '\'' +
				", completed='" + completed + '\'' +
				'}';
	}

	public int getProgress() {
		return progress;
	}

	public boolean isCompleted() {
		return completed;
	}


	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
}
