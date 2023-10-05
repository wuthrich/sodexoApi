package cl.sodexo.entity;

import java.time.OffsetDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Article {
	
	@Id
	private Long id;
	
	private String title;
	private String url;
	
	@Column(columnDefinition="TEXT", length = 2048)
	private String summary;	
	private OffsetDateTime published;
	private OffsetDateTime adding;
	
	public Article() {}	

	public Article(Long id, String title, String url, String summary, OffsetDateTime published,
			OffsetDateTime adding) {
		super();	
		
		this.id = id;
		this.title = title;
		this.url = url;
		this.summary = summary;
		this.published = published;		
		this.adding = adding;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public OffsetDateTime getPublished() {
		return published;
	}

	public void setPublished(OffsetDateTime published) {
		this.published = published;
	}

	public OffsetDateTime getAdding() {
		return adding;
	}
	public void setAdding(OffsetDateTime adding) {		
		this.adding = adding;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}