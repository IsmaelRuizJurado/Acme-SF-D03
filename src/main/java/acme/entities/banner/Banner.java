
package acme.entities.banner;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationMoment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startDisplayPeriod;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				endDisplayPeriod;

	@NotNull
	@NotBlank
	@URL
	private String				pictureLink;

	@NotNull
	@NotBlank
	@Length(min = 1, max = 75)
	private String				slogan;

	@NotNull
	@NotBlank
	@URL
	private String				webLink;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
