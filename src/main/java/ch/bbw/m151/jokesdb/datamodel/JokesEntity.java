package ch.bbw.m151.jokesdb.datamodel;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "jokes")
@Accessors(chain = true)
@Data
public class JokesEntity {

	@Id
	@GeneratedValue
	int id;

	@Column(nullable = false)
	String joke;

	@Version
	private int version;


	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;
}
