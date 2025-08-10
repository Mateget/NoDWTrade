package nodwtrade;

import com.pixelmonmod.pixelmon.api.events.PixelmonTradeEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;

import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PixelmonEventHandler {
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onTradeEvent(final PixelmonTradeEvent.Pre event) {
		if(!ConfigHandler.config.isTradingMachines()) return;
		boolean isCancel = false;
		if (event.getPlayer1() != null && isDWBreedable(event.getPokemon1())
				&& !PermissionHandler.playerHasDWTradePermission(event.getPlayer1())) {
			isCancel = true;
		}

		if (event.getPlayer2() != null && isDWBreedable(event.getPokemon2())
				&& !PermissionHandler.playerHasDWTradePermission(event.getPlayer2())) {
			isCancel = true;
		}
		if (isCancel) {
			StringTextComponent message = new StringTextComponent(
					ChatUtils.translateColourCodes('&', ConfigHandler.config.getNoTradeDWMessage()));
			if (event.getPlayer1() != null) {
				event.getPlayer1().sendMessage(message, event.getPlayer1().getUUID());
			}
			if (event.getPlayer2() != null) {
				event.getPlayer2().sendMessage(message, event.getPlayer2().getUUID());
			}
			event.setCanceled(true);
		}

	}

	private boolean isDWBreedable(Pokemon pokemon) {
		return pokemon != null && !pokemon.isUnbreedable() && pokemon.hasHiddenAbility();
	}

}