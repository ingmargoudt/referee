package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public class TargetCreatureOrPlayer implements Target{

    Targetable theTarget;


    public TargetCreatureOrPlayer(){

    }


    public boolean isValid(MagicObject source, Game game){
        return game.getBattlefield().getAll().stream().anyMatch(permanent -> permanent.getId().equals(theTarget.getId()));
    }

    @Override
    public List<Targetable> validTargets() {
        return new ArrayList<Targetable>();
    }

    @Override
    public Optional<Targetable> resolve(Game game) {
        Optional<Player> targettedPlayer = game.getPlayer(theTarget.getId());
        if(targettedPlayer.isPresent()){
           return Optional.of(targettedPlayer.get());
        }
        Optional<Permanent> targetedPermanent = game.getBattlefield().getAll().stream().filter(permanent -> permanent.getId().equals(theTarget.getId())).findFirst();
        if(targetedPermanent.isPresent()){
            return Optional.of(targetedPermanent.get());
        }
        return Optional.empty();
    }

    @Override
    public void choose(UUID source, Game game) {
        game.getPlayer(source).ifPresent(player -> {
            this.theTarget = player.chooseTarget(validTargets());
        });
    }


}
