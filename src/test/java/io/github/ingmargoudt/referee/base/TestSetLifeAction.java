package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Phase;

public class TestSetLifeAction extends TestPlayerAction {

    int life;

    public TestSetLifeAction(int turn , Phase phase, int life){
       super(turn, phase);
        this.life = life;
   }

   public  void execute(TestPlayer player){
    player.setLife(life);
   }
}
