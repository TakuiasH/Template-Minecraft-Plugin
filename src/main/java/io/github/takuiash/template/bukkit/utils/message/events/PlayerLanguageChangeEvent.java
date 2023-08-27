package io.github.takuiash.template.bukkit.utils.message.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import io.github.takuiash.template.bukkit.utils.message.lang.Language;

public class PlayerLanguageChangeEvent extends PlayerEvent {
	
    private final Language locale;
    private final Language oldLocale;

    public PlayerLanguageChangeEvent(final Player who, final Language locale, final Language oldLocale) {
        super(who);
        
        this.locale = locale;
        this.oldLocale = oldLocale;
    }

    /**
     * Gets the local of the player.
     *
     * @return The player's new locale.
     */
    public Language getLocale() {
        return locale;
    }

    /**
     * Gets the old local the player used before.
     *
     * @return The old player's locale. Null when had no locale before.
     */
    public Language getOldLocale() {
        return oldLocale;
    }

    private static final HandlerList HANDLERS = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}