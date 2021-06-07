package io.github.ingmargoudt.referee.game;

import java.util.LinkedList;

public class Turn {

    private LinkedList<Phase> phases = new LinkedList<>();

    /*
    500.1. A turn consists of five phases, in this order: beginning, precombat main, combat, postcombat
    main, and ending. Each of these phases takes place every turn, even if nothing happens during the
    phase. The beginning, combat, and ending phases are further broken down into steps, which
    proceed in order
    */
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
