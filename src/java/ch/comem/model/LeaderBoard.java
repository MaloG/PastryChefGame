/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Malo
 */
@NamedQuery(
    name="findAllLeaderBoards",
    query="SELECT l FROM LeaderBoard l"
)
@Entity
@XmlRootElement
public class LeaderBoard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    
    private List<Integer> rankingScore;
    private List<Long> rankingPlayer;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Application application;

    public List<Integer> getRankingScore() {
        return rankingScore;
    }

    public void setRankingScore(List<Integer> rankingScore) {
        this.rankingScore = rankingScore;
    }
    public void addRankingScore(int score){
        this.rankingScore.add(score);
    };

    public List<Long> getRankingPlayer() {
        return rankingPlayer;
    }

    public void setRankingPlayer(List<Long> rankingPlayer) {
        this.rankingPlayer = rankingPlayer;
    }
    public void setRankingPlayer(long playerId){
        this.rankingPlayer.add(playerId);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
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
        if (!(object instanceof LeaderBoard)) {
            return false;
        }
        LeaderBoard other = (LeaderBoard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.model.LeaderBoard[ id=" + id + " ]";
    }
    
}
