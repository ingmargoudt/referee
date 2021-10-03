package io.github.ingmargoudt.referee.game.effects;

import java.util.ArrayList;
import java.util.List;
public class Effects {


    private List<Effect> effectList = new ArrayList<>();

    public List<Effect> get(){
        return effectList;
    }

    public static Effects of(Effect effect){
        Effects effects = new Effects();
        effects.effectList.add(effect);
        return effects;
    }
}
