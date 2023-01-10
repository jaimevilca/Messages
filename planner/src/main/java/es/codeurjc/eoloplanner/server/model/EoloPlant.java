package es.codeurjc.eoloplanner.server.model;


public class EoloPlant {

	private Long id;

	private String city;
	private String planning;

	private int progress;

	private boolean completed;

	public EoloPlant(String city, String planning) {
		this.city = city;
		this.planning = planning;
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

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void addProgress(){
		this.setProgress(this.getProgress() + 25 );
		this.setCompleted(this.getProgress() == 100);
	}

	@Override
	public String toString() {
		return "EoloPlant{" +
				"id=" + id +
				", city='" + city + '\'' +
				", planning='" + planning + '\'' +
				", progress='" + progress + '\'' +
				", completed='" + completed + '\'' +
				'}';
	}
}
