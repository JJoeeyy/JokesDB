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

	@JoinColumn(name = "ratingId")
	@OneToOne(fetch = FetchType.LAZY)
	RatingsEntity ratingId;

	@Column(length = 500)
	String joke;

	@Version
	private int version;


	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;

	@Column
	String category;
	@Column
	String type;
	@Column
	String setup;
	@Column
	String delivery;
	@Column
	boolean nsfw;
	@Column
	boolean religious;
	@Column
	boolean political;
	@Column
	boolean racist;
	@Column
	boolean sexist;
	@Column
	boolean explicit;
	@Column
	boolean safe;
	@Column
	String lang;
}
