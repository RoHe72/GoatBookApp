package fr.rohe.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.owasp.html.PolicyFactory;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToOne(targetEntity = SiteUser.class)
	@JoinColumn(name = "user_id", nullable = false)
	private SiteUser user;

	@Column(name = "about", length = 5000)
	@Size(max = 5000, message = "{editprofile.about.size}")
	private String about;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "profile_interests", joinColumns = { @JoinColumn(name = "profile_id") }, inverseJoinColumns = {
			@JoinColumn(name = "interest_id") })
	@OrderColumn(name = "display_order")
	private Set<Interest> interests;

	public Profile() {

	}

	public Profile(SiteUser user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SiteUser getUser() {
		return user;
	}

	public void setUser(SiteUser user) {
		this.user = user;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void safeCopyFrom(Profile other) {
		if (other.about != null) {
			this.about = other.about;
		}

		if (other.interests != null) {
			this.interests = other.interests;
		}
	}

	/* Create a profile that is suitable for saving */
	public void safeMergeFrom(Profile webProfile, PolicyFactory htmlPolicy) {
		if (webProfile.about != null) {
			this.about = htmlPolicy.sanitize(webProfile.about);
		}
	}

	public Set<Interest> getInterests() {
		return interests;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}
	
	

	public void addInterest(Interest interest) {
		interests.add(interest);
	}

	public void removeInterest(String interestName) {
		interests.remove(new Interest(interestName));
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", user=" + user + ", about=" + about + ", photoDirectory=" + " interests="
				+ interests + "]";
	}

}