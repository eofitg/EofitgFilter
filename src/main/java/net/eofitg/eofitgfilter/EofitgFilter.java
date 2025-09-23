package net.eofitg.eofitgfilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.eofitg.eofitgfilter.command.EofitgFilterCommand;
import net.eofitg.eofitgfilter.config.EofitgFilterConfig;
import net.eofitg.eofitgfilter.listener.ChatListener;
import net.eofitg.eofitgfilter.util.Reference;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.FileUtils;

import java.io.File;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.MOD_VERSION,
        acceptedMinecraftVersions = "[1.8.9]",
        clientSideOnly = true
)
public class EofitgFilter {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static File configFile = null;
    public static EofitgFilterConfig config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        configFile = new File(e.getModConfigurationDirectory(), "eofitg-filter.json");
        loadConfig();
        Runtime.getRuntime().addShutdownHook(new Thread(EofitgFilter::saveConfig));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        ClientCommandHandler.instance.registerCommand(new EofitgFilterCommand());
    }

    public static void loadConfig() {
        if (configFile == null) return;
        if (configFile.exists()) {
            try {
                String json = FileUtils.readFileToString(configFile);
                config = gson.fromJson(json, EofitgFilterConfig.class);
            } catch (Exception ignored) {}
        } else {
            config = new EofitgFilterConfig();
            saveConfig();
        }
    }

    public static void saveConfig() {
        if (configFile == null) return;
        try {
            String json = gson.toJson(config);
            FileUtils.write(configFile, json);
        } catch (Exception ignored) {}
    }

}
