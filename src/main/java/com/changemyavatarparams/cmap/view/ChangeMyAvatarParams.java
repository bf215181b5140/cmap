package com.changemyavatarparams.cmap.view;

import com.changemyavatarparams.cmap.controller.OscController;
import com.changemyavatarparams.cmap.object.AvatarMeta;
import com.changemyavatarparams.cmap.object.Client;
import com.changemyavatarparams.cmap.object.ImageButton;
import com.changemyavatarparams.cmap.object.ParamsButton;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.RequestHandler;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinResponse;
import com.vaadin.flow.server.VaadinSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@PageTitle("Change my avatar params")
@Route("")
public class ChangeMyAvatarParams extends VerticalLayout {

    private List<ParamsButton> paramButtons = new ArrayList<>();

    private Client thisClient;


    Span onlineBadge = new Span(new Span("Online"), createIcon(VaadinIcon.CHECK));
    Span offlineBadge = new Span(new Span("Offline"), createIcon(VaadinIcon.CLOSE));
    Span afkBadge = new Span(new Span("AFK"), createIcon(VaadinIcon.CLOCK));
    Span sleepingBadge = new Span(new Span("Sleeping"), createIcon(VaadinIcon.BED));
    Span muteSelfBadge = new Span(new Span("Muted"), createIcon(VaadinIcon.MUTE));
    Span vrModeBadge = new Span(new Span("in VR"));
    Span desktopModeBadge = new Span(new Span("on desktop"));
    Span supportedAvatarBadge = new Span(new Span("Possibly using avatar that isn't supported!"), createIcon(VaadinIcon.WARNING));

    public ChangeMyAvatarParams() {
        // Skin
        paramButtons.add(new ParamsButton("Skin", 0, "Normal", "#746635"));
        paramButtons.add(new ParamsButton("Skin", 2, "Tan", "#437436"));
        paramButtons.add(new ParamsButton("Skin", 4, "Brown", "#75482d"));
        paramButtons.add(new ParamsButton("Skin", 3, "Succubus", "#4f688d"));
        paramButtons.add(new ParamsButton("Skin", 5, "Emo", "#743535"));
        paramButtons.add(new ParamsButton("Skin", 10, "Furry", "#367470"));

        // Outfits
        paramButtons.add(new ParamsButton("Outfit", 1, "Sporty"));
        paramButtons.add(new ParamsButton("Outfit", 0, "T-shirt"));
        paramButtons.add(new ParamsButton("Outfit", 5, "Sleeveless shirt"));
        paramButtons.add(new ParamsButton("Outfit", 6, "Tank top with skirt"));
        paramButtons.add(new ParamsButton("Outfit", 9, "Leather straps"));
        paramButtons.add(new ParamsButton("Outfit", 10, "Black leather"));
        paramButtons.add(new ParamsButton("Outfit", 11, "Summer maid"));
        paramButtons.add(new ParamsButton("Outfit", 12, "Leesh outfit"));

        // Hair
        paramButtons.add(new ParamsButton("Hair", false, "Blowout"));
        paramButtons.add(new ParamsButton("Hair", true, "Ponytail"));

        // Piercings
        paramButtons.add(new ParamsButton("Piercings", false, "Yes"));
        paramButtons.add(new ParamsButton("Piercings", true, "No"));

        // Horns and tail
        paramButtons.add(new ParamsButton("HornsTail", true, "Yes"));
        paramButtons.add(new ParamsButton("HornsTail", false, "No"));

        //Memes
        paramButtons.add(new ParamsButton("MemeToggles", 11, "Die in Minecraft"));
        paramButtons.add(new ParamsButton("MemeToggles", 12, "Die in Apex"));

        //Test button
//        paramButtons.add(new ParamsButton("MuteSelf", false, "testing button"));
//        paramButtons.add(new ParamsButton("MuteSelf", true, "testing button 2"));
//        paramButtons.add(new ParamsButton("TrackingType", 3, "testing button 2"));

        setId("pageLayout");
        setSizeFull();
        buildHeader();
        buildContent();
//        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        paramButtons.forEach(x -> x.addClickListener(y -> OscController.messageSend(x.getParam(), x.getValue(), true)));
    }

    private void buildHeader() {
        HorizontalLayout headerLayout = new HorizontalLayout();
        HorizontalLayout headerAvatar = new HorizontalLayout();
        HorizontalLayout headerMeta = new HorizontalLayout();

        Avatar avatar = new Avatar("Me");
        avatar.setImage("images/avatar.png");
        avatar.setId("avatarMe");

        onlineBadge.getElement().getThemeList().add("badge success");
        onlineBadge.addClassName("badge");
        onlineBadge.setVisible(false);

        offlineBadge.getElement().getThemeList().add("badge error");
        offlineBadge.addClassName("badge");
        offlineBadge.setVisible(false);

        afkBadge.getElement().getThemeList().add("badge error");
        afkBadge.addClassName("badge");
        afkBadge.setVisible(false);

        sleepingBadge.getElement().getThemeList().add("badge");
        sleepingBadge.addClassName("badge");
        sleepingBadge.setVisible(false);

        muteSelfBadge.getElement().getThemeList().add("badge error");
        muteSelfBadge.addClassName("badge");
        muteSelfBadge.setVisible(false);

        vrModeBadge.getElement().getThemeList().add("badge contrast");
        vrModeBadge.addClassName("badge");
        vrModeBadge.addClassName("vrMode");
        vrModeBadge.setVisible(false);

        desktopModeBadge.getElement().getThemeList().add("badge contrast");
        desktopModeBadge.addClassName("badge");
        desktopModeBadge.addClassName("desktopMode");
        desktopModeBadge.setVisible(false);

        supportedAvatarBadge.getElement().getThemeList().add("badge");
        supportedAvatarBadge.addClassName("badge");
        supportedAvatarBadge.addClassName("warning");
        supportedAvatarBadge.setVisible(false);

        headerAvatar.add(avatar, onlineBadge, offlineBadge);
        headerAvatar.setId("headerAvatar");
        headerAvatar.setAlignItems(Alignment.END);

        headerMeta.add(afkBadge, sleepingBadge, muteSelfBadge, vrModeBadge, desktopModeBadge);
        headerMeta.setId("headerMeta");
        headerMeta.setAlignItems(Alignment.END);

        headerLayout.add(headerAvatar, headerMeta);
        headerLayout.setId("headerLayout");
        headerLayout.setAlignItems(Alignment.END);
        headerLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        add(headerLayout);
        add(supportedAvatarBadge);
    }

    private void buildContent() {
        VerticalLayout contentLayout = new VerticalLayout();
        HorizontalLayout threeColumnLayout = new HorizontalLayout();
        HorizontalLayout skinlayout = new HorizontalLayout();
        skinlayout.setJustifyContentMode(JustifyContentMode.EVENLY);
        HorizontalLayout outfitLayout = new HorizontalLayout();
        outfitLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
        HorizontalLayout hairLayout = new HorizontalLayout();
        HorizontalLayout piercingsLayout = new HorizontalLayout();
        HorizontalLayout hornsTailLayout = new HorizontalLayout();
        HorizontalLayout memeLayout = new HorizontalLayout();
        HorizontalLayout otherLayout = new HorizontalLayout();

        paramButtons.forEach(btn -> {
            switch (btn.getParam()) {
                case "Skin":
                    skinlayout.add(new ImageButton(btn, null));
                    break;
                case "Outfit":
                    outfitLayout.add(new ImageButton(btn, "images/buttons/"));
                    break;
                case "MemeToggles":
                    memeLayout.add(btn);
                    break;
                case "Hair":
                    hairLayout.add(btn);
                    break;
                case "Piercings":
                    piercingsLayout.add(btn);
                    break;
                case "HornsTail":
                    hornsTailLayout.add(btn);
                    break;
                default:
                    otherLayout.add(btn);
                    break;
            }
        });

        VerticalLayout hairParentLayout = new VerticalLayout(new H4("Hair style"), hairLayout);
        hairParentLayout.setWidth("auto");
        hairParentLayout.setPadding(false);
//        hairParentLayout.setAlignItems(Alignment.CENTER);
        VerticalLayout piercingsParentLayout = new VerticalLayout(new H4("Piercings"), piercingsLayout);
        piercingsParentLayout.setWidth("auto");
        piercingsParentLayout.setPadding(false);
//        piercingsParentLayout.setAlignItems(Alignment.CENTER);
        VerticalLayout hornsTailParentLayout = new VerticalLayout(new H4("Horns and tail"), hornsTailLayout);
        hornsTailParentLayout.setWidth("auto");
        hornsTailParentLayout.setPadding(false);
//        hornsTailParentLayout.setAlignItems(Alignment.CENTER);

        threeColumnLayout.add(hairParentLayout, piercingsParentLayout, hornsTailParentLayout);
        threeColumnLayout.setWidthFull();
        threeColumnLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        contentLayout.add(
                new H4("Skin"),
                skinlayout,
                new H4("Outfits"),
                outfitLayout,
                threeColumnLayout,
                new H4("Fun"),
                memeLayout
//            new H4("Other"),
//            otherLayout
        );
        contentLayout.setId("contentLayout");
        contentLayout.setWidth("auto");
        contentLayout.setMaxWidth("65em");
        add(contentLayout);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {

        this.thisClient = new Client(attachEvent.getUI(), this, VaadinSession.getCurrent().getBrowser());
        attachEvent.getSession().addRequestHandler(new RequestHandler() {
            @Override
            public boolean handleRequest(VaadinSession session, VaadinRequest request, VaadinResponse response) throws IOException {
                if(thisClient.getHeaders().isEmpty()) {
                    Enumeration<String> names = request.getHeaderNames();
                    while (names.hasMoreElements()) {
                        String header = names.nextElement();
                        thisClient.getHeaders().put(header, request.getHeader(header));
                    }
                    System.out.println("----------- Added new client! -----------");
                    System.out.println(thisClient.getHeaders().get("cookie"));
                    System.out.println(thisClient.getHeaders().get("cf-connecting-ip"));
                    OscController.addClient(thisClient);
                }
                return false;
            }
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        OscController.removeClient(this.thisClient);
    }

    public void updateContent(AvatarMeta avatarMeta) {
        onlineBadge.setVisible(avatarMeta.isOnline());
        offlineBadge.setVisible(!avatarMeta.isOnline());
        if (avatarMeta.isOnline()) {
            afkBadge.setVisible(avatarMeta.isAfk());
            muteSelfBadge.setVisible(avatarMeta.isMuteSelf());
            vrModeBadge.setVisible(avatarMeta.isInVrMode());
            desktopModeBadge.setVisible(!avatarMeta.isInVrMode());
            supportedAvatarBadge.setVisible(!avatarMeta.isSupportedAvatar());
            AtomicInteger sleepingThreshold = new AtomicInteger(0);
            paramButtons.forEach(btn -> {
                btn.setEnabled(true);
                avatarMeta.getAvatarParameters().forEach(newParam -> {
                    if (btn.getParam().equals(newParam.getParam()) && btn.getValue().equals(newParam.getValue())) {
                        btn.setEnabled(false);
                    }
                    if (newParam.getParam().equals("GestureRight") && newParam.getValue().equals(7) ||
                            newParam.getParam().equals("GestureLeft") && newParam.getValue().equals(0)) {
                        sleepingThreshold.getAndIncrement();
                    }
                });
            });
            sleepingBadge.setVisible(avatarMeta.isInVrMode() && !avatarMeta.isAfk() && sleepingThreshold.get() >= (paramButtons.size() * 2));
        } else {
            afkBadge.setVisible(false);
            muteSelfBadge.setVisible(false);
            vrModeBadge.setVisible(false);
            desktopModeBadge.setVisible(false);
            sleepingBadge.setVisible(false);
            supportedAvatarBadge.setVisible(false);
            paramButtons.forEach(x -> x.setEnabled(false));
        }
    }

    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

}
