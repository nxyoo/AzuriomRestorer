package org.nxyo.azuriomrestorer;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;

import java.io.File;
import java.io.IOException;

public class Main extends Plugin implements Listener {

    private SkinsRestorerAPI skinsRestorerAPI;
    private Configuration configuration;

    @Override
    public void onEnable() {
        getLogger().info("§7Plugin RestorerWeb by §3AlouchiCC§7, Ported to bungeecord by §3nxyo");
        getProxy().getPluginManager().registerListener(this, this);
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        skinsRestorerAPI = SkinsRestorerAPI.getApi();
    }

    @EventHandler
    public void Skins(ServerConnectEvent event) throws SkinRequestException {
        ProxiedPlayer player = event.getPlayer();
        String url = configuration.getString("url");
        skinsRestorerAPI.setSkin(player.getName(), url);

    }

    @Override
    public void onDisable() {
        System.out.println("§7Plugin RestorerWeb by §3AlouchiCC§7, Ported to bungeecord by §3nxyo");
    }
}
