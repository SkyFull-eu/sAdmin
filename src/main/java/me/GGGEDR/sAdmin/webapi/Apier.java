package me.GGGEDR.sAdmin.webapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;
import me.GGGEDR.sAdmin.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Apier {

    public static void startApiServerInterface() throws IOException {
        HttpServer http = HttpServer.create(new InetSocketAddress(25797), 1200);

        http.createContext("/data", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String nick = httpExchange.getRequestURI().toString().split("\\?player=")[1];
                JSONObject json = new JSONObject();
                json.put("nickname", nick);
                if(Bukkit.getPlayer(nick) != null){
                    Player p = Bukkit.getPlayer(nick);
                    json.put("status", "online");
                    json.put("rank", PlaceholderAPI.setPlaceholders(p, "%luckperms_primary_group_name%"));
                    json.put("coins", PlaceholderAPI.setPlaceholders(p, "%vault_eco_balance_formatted%"));
                    json.put("tokens", PlaceholderAPI.setPlaceholders(p, "%tm_tokens_formatted%"));
                } else {
                    json.put("status", "offline");
                }
                closeJson(httpExchange, json.toString());
            }
        });

        http.start();
    }

    private static void closeJson(HttpExchange httpExchange, String bin) throws IOException {
        String encoding = "UTF-8";
        httpExchange.getResponseHeaders().set("Content-Type", "text/json; charset=" + encoding);
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        byte[] bytes = bin.getBytes(StandardCharsets.UTF_8);
        httpExchange.sendResponseHeaders(200, bytes.length);
        httpExchange.getResponseBody().write(bytes);
        httpExchange.close();
    }
}
