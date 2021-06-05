package io.github.ingmargoudt.referee.game;

import java.util.LinkedList;

public class Turn {

    private LinkedList<Phase> phases = new LinkedList<>();


    public Turn(){
        phases.add(Phase.BEGINNING_PHASE);
        phases.add(Phase.PRECOMBAT_MAINPHASE);
        phases.add(Phase.COMBAT_PHASE);
        phases.add(Phase.POSTCOMBAT_MAINPHASE);
        phases.add(Phase.END_PHASE);
    }

    public void run(Game game){
        while(!phases.isEmpty()){
            phases.pop().run(game);
        }
    }
}
