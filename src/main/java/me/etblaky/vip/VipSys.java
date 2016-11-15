package me.etblaky.vip;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by ETblaky on 09/11/2016.
 */
public class VipSys extends JavaPlugin implements Listener{

    public void onEnable(){
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    public static boolean isVip(Player p){

        //System.out.println(p.getUniqueId());

        try {

            String a = "http://localhost/VipKangarooServer/php/sv/isVip?uuid=" + p.getUniqueId().toString();
            URLConnection connection = new URL(a).openConnection();
            connection.connect();

            BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

            String page = "";
            String line;
            while ((line = r.readLine()) != null) {
                page += line;
            }

            //System.out.println(page);

            if(page.trim().equalsIgnoreCase("true")){
                return true;
            }

        }catch (Exception e){ e.printStackTrace(); }

        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(isVip(e.getPlayer())){
            e.getPlayer().sendMessage("Bem Vindo! Obrigado por ser Vip!");
        }
    }

}
