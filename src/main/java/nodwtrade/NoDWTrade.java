package nodwtrade;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pixelmonmod.pixelmon.Pixelmon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod(NoDWTrade.MODID)
public class NoDWTrade {
	private static String modName = "NoDWTrade";
	public static final String MODID = "nodwtrade"; 
    public static File configDir;
	
    public static final Logger LOGGER = LogManager.getLogger(modName);

    public NoDWTrade() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.<Supplier<String>, BiPredicate<String, Boolean>>of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));

    }

    private void setup(final FMLCommonSetupEvent event) {
    	configDir = new File("config" + FileSystems.getDefault().getSeparator() + MODID + FileSystems.getDefault().getSeparator());
        configDir.mkdir();
        
        ConfigHandler.readAllConfigs();
        ConfigHandler.creationCheckConfig();
        ConfigHandler.writeConfig();

    }
    
    @SubscribeEvent
    public void preInit(FMLServerAboutToStartEvent e) {
    	MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
        Pixelmon.EVENT_BUS.register(new PixelmonEventHandler());
	}

}
