package com.changemyavatarparams.cmap.controller;

import com.changemyavatarparams.cmap.object.AvatarMeta;
import com.changemyavatarparams.cmap.object.AvatarParameter;
import com.changemyavatarparams.cmap.object.Client;
import com.illposed.osc.MessageSelector;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCMessageListener;
import com.illposed.osc.messageselector.OSCPatternAddressMessageSelector;
import com.illposed.osc.transport.udp.OSCPortIn;
import com.illposed.osc.transport.udp.OSCPortOut;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OscController {

    // Osc
    // --osc=9000:192.168.1.100:9001
    private static final String oscIP = "192.168.1.50";
    private static final int inPort = 9006;
    private static final int outPort = 9005;
    private static OSCPortOut oscPortOut;
    private static OSCPortIn oscPortIn;
    private static final DbController dbConnection = new DbController();

    // Avatar meta and parameters
    public static AvatarMeta avatarMeta = new AvatarMeta();
    public static List<String> supportedAvatarIDs = Arrays.asList("avtr_c4368f6c-cf31-4763-90ab-e6e56ee7f009");

    // UI Clients
    public static List<Client> clients = new ArrayList<>();

    public static void addClient(Client newClient) {
        dbConnection.addClient(newClient);
        clients.add(newClient);
        newClient.getUi().access(() -> newClient.getView().updateContent(avatarMeta));
    }

    public static void removeClient(Client oldClient){
        clients.remove(oldClient);
    }

    public static void sendParams(){
        Iterator<Client> clientIterator = clients.iterator();
        while(clientIterator.hasNext()) {
            Client x = clientIterator.next();
            try {
                x.getUi().access(() -> x.getView().updateContent(avatarMeta));
            } catch (Exception e) {
                System.out.println(java.time.LocalDateTime.now() + " Failed to send a parameter to specific client from list. " + clients.size() + " clients: " + clients.toString() + ". Problematic client: " + x.toString() + ". Exception message: " + e.getMessage());
                clientIterator.remove();
            }
        }
    }

    public static boolean onlineCheck(){
        boolean online = avatarMeta.getLastPulse().plusMinutes(4).isAfter(java.time.LocalDateTime.now());
        if(online != avatarMeta.isOnline()) {
            avatarMeta.setOnline(online);
            return online;
        }
        return !online;
    }

    public static void messageSend(AvatarParameter avatarParameter, Boolean writeToDb) {
        try {
            oscPortOut = new OSCPortOut(InetAddress.getByName(oscIP), outPort);
        } catch(Exception e) {
            System.out.println(java.time.LocalDateTime.now() + " Failed setting up a OSCPortOut connection to send a message. Exception: " + e.getMessage());
        }

        OSCMessage message = new OSCMessage("/avatar/parameters/" + avatarParameter.getParam(), List.of(avatarParameter.getValue()));

        try {

            oscPortOut.send(message);

            if (writeToDb) {
                dbConnection.addParameter(avatarParameter.getParam(), avatarParameter.getValue());
            }

        } catch (Exception e) {
            System.out.println(java.time.LocalDateTime.now() + " Failed sending a manual OSC message: " + message.toString() + ". Exception: " + e.getMessage());
        }
    }

    public static void messageRecieve() {
        try {
            oscPortIn = new OSCPortIn(inPort);

            MessageSelector paramSelector = new OSCPatternAddressMessageSelector("/avatar/parameters/*");
            MessageSelector avatarSelector = new OSCPatternAddressMessageSelector("/avatar/change");

            OSCMessageListener paramListener = event -> {
                String parameter = StringUtils.substringAfterLast(event.getMessage().getAddress(), "/");
                Object value = event.getMessage().getArguments().get(0);
                avatarMeta.setLastPulse(java.time.LocalDateTime.now());
                switch(parameter){
                    case "AFK":
                        avatarMeta.setAfk((boolean) value);
                        break;
                    case "MuteSelf":
                        avatarMeta.setMuteSelf((boolean) value);
                        break;
                    case "VRMode":
                        avatarMeta.setVrMode((int) value == 1);
                        break;
                    default:
                        boolean paramExists = false;
                        for(AvatarParameter param : avatarMeta.getAvatarParameters()){
                            if(parameter.equals(param.getParam())){
                                param.setValue(value);
                                paramExists = true;
                            }
                        }
                        if(!paramExists){
                            avatarMeta.getAvatarParameters().add(new AvatarParameter(StringUtils.substringAfterLast(event.getMessage().getAddress(), "/"), (event.getMessage().getArguments().get(0))));
                        }
                        break;
                }
                onlineCheck();
                sendParams();
            };

            OSCMessageListener avatarListener = event -> {
                Object value = event.getMessage().getArguments().get(0);
                avatarMeta.setLastPulse(java.time.LocalDateTime.now());
                avatarMeta.setSupportedAvatar(supportedAvatarIDs.contains(value.toString()));
                onlineCheck();
                sendParams();
            };

            oscPortIn.getDispatcher().addListener(paramSelector, paramListener);
            oscPortIn.getDispatcher().addListener(avatarSelector, avatarListener);
            oscPortIn.getDispatcher().setAlwaysDispatchingImmediately(true);
            oscPortIn.startListening();
        } catch (Exception e){
            System.out.println(java.time.LocalDateTime.now() + " Failed to receive an OSC message. Exception: " + e.getMessage());
        }
    }


}
