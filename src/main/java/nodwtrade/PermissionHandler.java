package nodwtrade;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class PermissionHandler {
	
	public static final String BASE =NoDWTrade.MODID+".";
	public static final String ADMIN = "admin.";
	
	public static final String CAN_TRADE = BASE + ADMIN + "cantrade";
	
	
	
	
	public static void init() {
		PermissionAPI.registerNode(CAN_TRADE, DefaultPermissionLevel.OP, "Can tarde DW Breedable");
	}
		
	public static boolean playerHasPermission(PlayerEntity playerEntity, String node) {
		return PermissionAPI.hasPermission(playerEntity, node);
	}
	
	public static boolean playerHasDWTradePermission(PlayerEntity playerEntity) {
		return playerHasPermission(playerEntity,CAN_TRADE);
	}


}
