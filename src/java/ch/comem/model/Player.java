/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Malo
 */
@NamedQueries({
    @NamedQuery(
            name="findAllPlayers",
            query="SELECT p FROM Player p"
    ),
    @NamedQuery(name="findPlayerIdFromMemberId",
                query="SELECT p.id FROM Player p WHERE p.memberId = :memberId"),
    @NamedQuery(
            name="findPlayerPoints",
            query="SELECT p FROM Player p WHERE p.id = :playerId"
    ),
    @NamedQuery(
            name="findPlayerBadges",
            query="SELECT b FROM Badge b JOIN b.players p WHERE p.id = :playerId"
    )
})
@Entity
@XmlRootElement
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String email;
    private Integer numberOfPoints = new Integer(0);
    private Long memberId;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="player_badge",
               joinColumns=@JoinColumn(name="players_ID"),
               inverseJoinColumns=@JoinColumn(name="badges_ID"))
    private List<Badge> badges = new LinkedList<Badge>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Application application;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="player_event",
               joinColumns=@JoinColumn(name="Player_ID"),
               inverseJoinColumns=@JoinColumn(name="events_ID"))
    private List<Event> events = new LinkedList<Event>();

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
    public void addEvents(Event event){
        this.events.add(event);
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public void addBadges(Badge badge) {
        getBadges().add(badge);
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints += numberOfPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.model.Player[ id=" + id + " ]";
    }
    
}
