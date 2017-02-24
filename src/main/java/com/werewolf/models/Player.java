package com.werewolf.models;


import com.werewolf.controllers.GameMessageBroker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String sessionId;
    private int seatId;
    private boolean ready;
    private boolean alive;
    private boolean campaign;
    private boolean sheriff;
    private Role role;

    private Game game;
    @Autowired
    private GameMessageBroker messageBroker;

    public Player(Game game, String sessionId, int seatId, Role role) {
        this.sessionId = sessionId;
        this.seatId = seatId;
        this.role = role;
        ready = false;
        alive = true;
        campaign = false;
        sheriff = false;
        this.game = game;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isCampaign() {
        return campaign;
    }

    public void campaignForSheriff() {
        this.campaign = true;
    }

    public boolean isSheriff() {
        return sheriff;
    }

    public void setSheriff(boolean sheriff) {
        this.sheriff = true;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public Map<Integer, Integer> vote(int seatId){
        Map<Integer, Integer> voteResult = new HashMap<>();
        voteResult.put(this.getSeatId(), seatId);
        return voteResult;
    }

    public List<String> skills(){
        List<String> skills = this.getRole().getSkills();
        if(isCampaign()){
            skills.remove("voteForCampaign");
        }
        return skills;
    }

    public boolean isJudge() {
        return this.sessionId.equals(game.getJudge().getSessionId());
    }

}
