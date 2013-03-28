/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.Badge;
import ch.comem.model.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Malo
 */
@Stateless
public class PlayersManager implements PlayersManagerLocal {
    @PersistenceContext(unitName = "PastryChefGame")
    private EntityManager em;

    
    @Override
    public long createPlayer(String firstName, String lastName, String email, long applicationId, long memberId) {
        Player player = new Player();
        
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);
        player.setNumberOfPoints(new Integer(0));
        player.setMemberId(memberId);
        
        Application application = em.find(Application.class, applicationId);
        
        if(application != null){
            
            player.setApplication(application);
            application.addPlayers(player);
            
        }
        persist(player);
        em.flush();
        return player.getId();
    }
    
    

    @Override
    public void addBadgeToPlayer(long badgeId, long playerId) {
        
        Player player = em.find(Player.class, playerId);
        Badge badge = em.find(Badge.class, badgeId);
        
        if(player != null && badge != null){
            
            player.addBadges(badge);
            badge.addPlayer(player);


        }
        
        
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Player> findAll() {
        List<Player> players;
        players = em.createNamedQuery("findAllPlayers").getResultList();
        return players;
    }

    @Override
    public List<Badge> getPlayerBadges(long playerId) {
        List<Badge> badges;
        badges = em.createNamedQuery("findPlayerBadges").setParameter("playerId", playerId).getResultList();
        return badges;
    }

    @Override
    public List<Player> getPlayerPoints(long playerId) {
        List<Player> player;
        player = em.createNamedQuery("findPlayerPoints").setParameter("playerId", playerId).getResultList();
        return player;
    }

    @Override
    public Long findPlayerIdFromMemberId(Long memberId) {
        return (Long) em.createNamedQuery("findPlayerIdFromMemberId").setParameter("memberId", memberId).getSingleResult();
    }

    
    
}
