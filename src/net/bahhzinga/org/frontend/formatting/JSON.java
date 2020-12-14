package net.bahhzinga.org.frontend.formatting;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class JSON {
	
	public static void send(Player player, JSONType type, String message, String tooltip, String output) {
		TextComponent msg = new TextComponent( ChatColor.translateAlternateColorCodes('&', message) );
		switch (type) {
		case RUN_COMMAND:
			msg.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, output ) );
			break;
		case SUGGEST_COMMAND:
			msg.setClickEvent(new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, output ) );
			break;
		case LINK:
			msg.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, output ) );
			break;
		case OPEN_FILE:
			msg.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_FILE, output ) );
			break;
		}
		msg.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new Text( ChatColor.translateAlternateColorCodes('&', tooltip) ) ) );
		
		player.spigot().sendMessage(msg);
		
	}

}
