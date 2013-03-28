/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.Badge;
import ch.comem.model.Event;
import ch.comem.model.Player;
import ch.comem.model.Rule;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Malo
 */
@Stateless
public class EventsManager implements EventsManagerLocal {
    @EJB
    private PlayersManagerLocal pm;
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;
    
    private int defineExperienceLevel(Player p) {
        Integer points = p.getNumberOfPoints();
        int flag = 6;
        if (points.intValue() >= 2000)
            flag = 1;
        else if (points.intValue() >= 1400)
            flag = 2;
        else if (points.intValue() >= 900)
            flag = 3;
        else if (points.intValue() >= 500)
            flag = 4;
        else if (points.intValue() >= 200)
            flag = 5;
        return flag;
    }
    
    private void updateExperienceBadge(Player p, Application a, int flag) {
        List<Badge> bList = p.getBadges();
        List<Rule> rList = a.getRules();
        boolean updated = false;
        switch(flag) {
            case 1:
                for (Rule r : rList) {
                    if (!updated) {
                        if (r.getOnEventType().equals("Expérience acquise") && 
                            r.getNumberOfPoints() == 2000) {
                            bList.remove(0);
                            bList.add(0, r.getBadge());
                            updated = true;
                        }
                    }
                }
                break;
            case 2:
                for (Rule r : rList) {
                    if (!updated) {
                        if (r.getOnEventType().equals("Expérience acquise") && 
                            r.getNumberOfPoints() == 1400) {
                            bList.remove(0);
                            bList.add(0, r.getBadge());
                            updated = true;
                        }
                    }
                }
                break;
            case 3:
                for (Rule r : rList) {
                    if (!updated) {
                        if (r.getOnEventType().equals("Expérience acquise") && 
                            r.getNumberOfPoints() == 900) {
                            bList.remove(0);
                            bList.add(0, r.getBadge());
                            updated = true;
                        }
                    }
                }
                break;
            case 4:
                for (Rule r : rList) {
                    if (!updated) {
                        if (r.getOnEventType().equals("Expérience acquise") && 
                            r.getNumberOfPoints() == 500) {
                            bList.remove(0);
                            bList.add(0, r.getBadge());
                            updated = true;
                        }
                    }
                }
                break;
            case 5:
                for (Rule r : rList) {
                    if (!updated) {
                        if (r.getOnEventType().equals("Expérience acquise") && 
                            r.getNumberOfPoints() == 200) {
                            bList.remove(0);
                            bList.add(0, r.getBadge());
                            updated = true;
                        }
                    }
                }
                break;
        }
    }
    
    @Override
    public long createEvent(Long memberId, long applicationId, String type, 
                              long timeInMillis) {
        Event event = new Event();
        Long id = null;
        Long playerId = pm.findPlayerIdFromMemberId(memberId);
        Player player = em.find(Player.class, playerId);
        Application application = em.find(Application.class, applicationId);
        
        if(player != null && application != null){
        
            int flag = defineExperienceLevel(player);

            event.setType(type);
            event.setTimeInMillis(timeInMillis);
            
            event.setPlayer(player);
            player.addEvents(event);
            
            event.setApplication(application);
            application.addEvent(event);
            
            List<Rule> rList = application.getRules();
            boolean validEvent = false;
            for (Rule r : rList) {
                if (!validEvent) {
                    if (r.getOnEventType().equals(type)) { 
                        Integer points = r.getNumberOfPoints();
                        Badge b = r.getBadge();
                        if (b != null) {
                            if(!player.getBadges().contains(b)) {
                                player.addBadges(b);
                                player.setNumberOfPoints(points);
                            }
                        } else
                            player.setNumberOfPoints(points);
                        validEvent = true;
                    }
                }
            }
     
            persist(event);
            em.flush();

            int flag2 = defineExperienceLevel(player);
            if (flag > flag2)
                updateExperienceBadge(player, application, flag2);
            
            em.flush();
            id = event.getId();
        }
        return id;
    }
    @Override
    public List<Event> findAll(){
        List<Event> list;
        list = em.createNamedQuery("findAllEvents").getResultList();
        return list;
    } 

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Event> getPlayerEvents(Long appId, Long playerId) {
        List<Event> list;
        list = em.createNamedQuery("getPlayerEvents").setParameter("appId", appId)
                                                    .setParameter("playerId", playerId)
                                                    .getResultList();
        return list;
    }
    
    

}
