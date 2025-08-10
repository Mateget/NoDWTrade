package nodwtrade;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeEventHandler {
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onCommandEvent(CommandEvent event) {
		if(!ConfigHandler.config.isPokesell()) return;
		boolean isCancel = false;
		// Check if command is /pokesell
		if (event.getParseResults() != null && event.getParseResults().getReader().getString().startsWith("/pokesell")) {
			// Get arguments
			String[] args = event.getParseResults().getReader().getString().split(" ");
			if (args.length >= 3) {
				try {
					int slot = Integer.parseInt(args[2]); // slot is now the third argument
					// Get player executing command
					ServerPlayerEntity player = null;
					try {
						player = event.getParseResults().getContext().getSource().getPlayerOrException();
					} catch (Exception ex) {
						// Not a player command source
					}
					if (player != null) {
						PlayerPartyStorage storage = StorageProxy.getParty(player);
						if (storage != null && slot >= 1 && slot <= 6) {
							Pokemon pokemon = storage.get(slot - 1);
							if (isDWBreedable(pokemon) && !PermissionHandler.playerHasDWTradePermission(player)) {
								isCancel = true;
							}
						}
					}
				} catch (Exception e) {
					// Ignore parse errors
				}
			}
			if (isCancel) {
				ServerPlayerEntity player = null;
				try {
					player = event.getParseResults().getContext().getSource().getPlayerOrException();
				} catch (Exception ex) {
					// Not a player command source
				}
				if (player != null) {
					player.sendMessage(new StringTextComponent(ChatUtils.translateColourCodes('&', ConfigHandler.config.getNoTradeDWMessage())), player.getUUID());
				}
				event.setCanceled(true);
			}
		}
	}
	
	private boolean isDWBreedable(Pokemon pokemon) {
		return pokemon != null && !pokemon.isUnbreedable() && pokemon.hasHiddenAbility();
	}

}
