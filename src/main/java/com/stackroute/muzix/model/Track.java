package com.stackroute.muzix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*creates a table track with id, name, comment columns using @Entity @Id @Column annotation */
/*lombok annotations @Data @AllArgsConstructor @NoArgsConstructor are used for creating constructor, getter and setter*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Track {
	@Id
//	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String comment;
}
