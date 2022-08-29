package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.properties.Counterable;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;

import java.util.UUID;

public class Spell extends MagicObject implements Stackable, Counterable, Targetable {

    @Getter
    private final Card card;


    public Spell(Card card) {
        super(card.getName());
        this.card = card;
        this.cardtypes = card.getCardtypes();
        this.spellEffects = card.getSpellEffects();
    }

    public void resolve(Game game) {
        if (card.isPermanent()) {
            game.moveToBattlefield(card);
        } else {
            getSpellEffects().apply(this, game);
        }

    }

    @Override
    public Player getController() {
        return card.getController();
    }

    @Override
    public MagicObject getSource() {
        return card;
    }

    @Override
    public String getName() {
        return card.getName();
    }

    @Override
    public boolean hasTargets() {
        return card.getSpellEffects().hasTargets();
    }

    @Override
    public Effects<OneShotEffect> getEffects() {
        return card.getSpellEffects();
    }


    @Override
    public void counter(Game game) {
        game.getStack().counter(this);
    }

    @Override
    public UUID getId() {
        return card.getId();
    }
}
