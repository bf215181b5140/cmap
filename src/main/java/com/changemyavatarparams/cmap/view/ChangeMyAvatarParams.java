package com.changemyavatarparams.cmap.view;

import com.changemyavatarparams.cmap.controller.OscController;
import com.changemyavatarparams.cmap.object.*;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Change my avatar params")
@Route("")
public class ChangeMyAvatarParams extends VerticalLayout {

    private List<ParamButton> paramButtons = new ArrayList<>();

    private Client thisClient;

    Span onlineBadge = new Span(new Span("Online"), createIcon(VaadinIcon.CHECK));
    Span offlineBadge = new Span(new Span("Offline"), createIcon(VaadinIcon.CLOSE));
    Span afkBadge = new Span(new Span("AFK"), createIcon(VaadinIcon.CLOCK));
    Span sleepingBadge = new Span(new Span("Sleeping"), createIcon(VaadinIcon.BED));
    Span muteSelfBadge = new Span(new Span("Muted"), createIcon(VaadinIcon.MUTE));
    Span vrModeBadge = new Span(new Span("in VR"));
    Span desktopModeBadge = new Span(new Span("on desktop"));
    Span supportedAvatarBadge = new Span(new Span("Possibly using avatar that isn't supported!"), createIcon(VaadinIcon.WARNING));
    Span expInfo = new Span("Most toggles have exp cost. If there isn't enough exp they will be grayed out. Touching certain parts of my avatar in VRChat increases exp. Dying animation toggles consume most of the exp points.");

    ExpBarLayout expBar = new ExpBarLayout();
    HpBarLayout hpBar = new HpBarLayout();

    VerticalLayout contentLayout = new VerticalLayout();
    Avatar avatar = new Avatar("Me");
    Div socksLayout = new Div();

    public ChangeMyAvatarParams() {

        // Skin
        paramButtons.add(new ImageButton(new AvatarParameter("Skin", 0, 2)));
        paramButtons.add(new ImageButton(new AvatarParameter("Skin", 2, 2)));
        paramButtons.add(new ImageButton(new AvatarParameter("Skin", 4, 2)));
        paramButtons.add(new ImageButton(new AvatarParameter("Skin", 3, 2)));
        paramButtons.add(new ImageButton(new AvatarParameter("Skin", 5, 2)));
        paramButtons.add(new ImageButton(new AvatarParameter("Skin", 10, 2)));
        paramButtons.add(new ImageButton(new AvatarParameter("Skin", 6, 2)));
        paramButtons.add(new ImageButton(new AvatarParameter("Skin", 7, 2)));

        // Outfits
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 1, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 0, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 5, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 6, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 9, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 10, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 11, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 12, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("Outfit", 13, 1)));

//        ParamButton styleOutfitTrue = new VerticalImageButton(new AvatarParameter("StyleOutfit", true, 2));
//        styleOutfitTrue.getStyle().set("background-position", "center center");
//        ParamButton styleOutfitFalse = new VerticalImageButton(new AvatarParameter("StyleOutfit", false, 2));
//        styleOutfitFalse.getStyle().set("background-position", "center center");
//        styleOutfitFalse.setVisible(false);
//        paramButtons.add(styleOutfitTrue);
//        paramButtons.add(styleOutfitFalse);

        paramButtons.add(new VerticalImageButton(new AvatarParameter("StyleSocks", 0, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("StyleSocks", 1, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("StyleSocks", 2, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("StyleSocks", 3, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("StyleSocks", 4, 1)));
        paramButtons.add(new VerticalImageButton(new AvatarParameter("StyleSocks", 5, 1)));

        // Hair
        paramButtons.add(new ParamButton(new AvatarParameter("Hair", false, 1), "Blowout"));
        paramButtons.add(new ParamButton(new AvatarParameter("Hair", true, 1), "Ponytail"));

        // Piercings
        paramButtons.add(new ParamButton(new AvatarParameter("Piercings", false, 1), "Yes"));
        paramButtons.add(new ParamButton(new AvatarParameter("Piercings", true, 1), "No"));

        // Horns and tail
        paramButtons.add(new ParamButton(new AvatarParameter("HornsTail", true, 1), "Yes"));
        paramButtons.add(new ParamButton(new AvatarParameter("HornsTail", false, 1), "No"));

        //Memes
        paramButtons.add(new ParamButton(new AvatarParameter("MemeToggles", 11, integer -> Math.max(1, (int) (integer * 0.3))), "Die in Minecraft"));
        paramButtons.add(new ParamButton(new AvatarParameter("MemeToggles", 12, integer -> Math.max(1, (int) (integer * 0.3))), "Die in Apex"));
//        paramButtons.add(new ParamButton(new AvatarParameter("takeDamage", true, 0), "A little slap"));

        //Test button
//        paramButtons.add(new ParamsButton("MuteSelf", false, "testing button"));
//        paramButtons.add(new ParamsButton("MuteSelf", true, "testing button 2"));
//        paramButtons.add(new ParamsButton("TrackingType", 3, "testing button 2"));

        setId("pageLayout");
        buildHeader();
        buildContent();
//        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

//        paramButtons.forEach(x -> x.addClickListener(y -> OscController.messageSend(x.getParam(), x.getValue(), true)));
    }

    private void buildHeader() {
        HorizontalLayout headerLayout = new HorizontalLayout();
        VerticalLayout headerMeta = new VerticalLayout();

        HorizontalLayout meta = new HorizontalLayout();

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

        expInfo.getElement().getThemeList().add("badge");
        expInfo.addClassName("badge");
        expInfo.setMaxWidth("500px");

//        headerAvatar.add(, onlineBadge, offlineBadge);
//        headerAvatar.setId("headerAvatar");
//        headerAvatar.setAlignItems(Alignment.END);

        meta.add(new HorizontalLayout(onlineBadge, offlineBadge), new HorizontalLayout(afkBadge, sleepingBadge, muteSelfBadge, vrModeBadge, desktopModeBadge));
        meta.setJustifyContentMode(JustifyContentMode.BETWEEN);
        meta.setId("meta");
//        meta.setAlignItems(Alignment.END);

        headerMeta.add(meta, hpBar, expBar);
        headerMeta.setJustifyContentMode(JustifyContentMode.CENTER);
        headerMeta.setAlignItems(Alignment.END);
        headerMeta.setId("headerMeta");

        headerLayout.add(avatar, headerMeta);
        headerLayout.setId("headerLayout");
        headerLayout.setAlignItems(Alignment.CENTER);
        headerLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        add(headerLayout);
        add(supportedAvatarBadge, expInfo);
    }

    private void buildContent() {

        HorizontalLayout skinlayout = new HorizontalLayout();
        skinlayout.setJustifyContentMode(JustifyContentMode.EVENLY);

        HorizontalLayout outfitLayout = new HorizontalLayout();
        outfitLayout.setJustifyContentMode(JustifyContentMode.EVENLY);

        HorizontalLayout socksStyleLayout = new HorizontalLayout();
        socksStyleLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
        socksLayout.add(new H4("Socks style"), socksStyleLayout);

        HorizontalLayout threeColumnLayout = new HorizontalLayout();
        GroupButtonLayout hairLayout = new GroupButtonLayout();
        GroupButtonLayout piercingsLayout = new GroupButtonLayout();
        GroupButtonLayout hornsTailLayout = new GroupButtonLayout();

        GroupButtonLayout memeLayout = new GroupButtonLayout();
        GroupButtonLayout otherLayout = new GroupButtonLayout();

        paramButtons.forEach(btn -> {
            switch (btn.getAvatarParameter().getParam()) {
                case "Skin":
                    skinlayout.add(btn);
                    break;
                case "Outfit":
                case "StyleOutfit":
                    outfitLayout.add(btn);
                    break;
                case "StyleSocks":
                    socksStyleLayout.add(btn);
                    break;
                case "MemeToggles":
                    memeLayout.add(btn);
                    break;
                case "takeDamage":
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
                socksLayout,
                threeColumnLayout,
                new H4("Fun"),
                memeLayout
//            new H4("Other"),
//            otherLayout
        );
        contentLayout.setId("contentLayout");
        add(contentLayout);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        this.thisClient = new Client(attachEvent.getUI(), this, VaadinSession.getCurrent().getBrowser());
        OscController.addClient(thisClient);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        OscController.removeClient(this.thisClient);
    }

    public void updateContent(AvatarMeta avatarMeta) {
        onlineBadge.setVisible(avatarMeta.isOnline());
        offlineBadge.setVisible(!avatarMeta.isOnline());
        if (avatarMeta.isOnline()) {
            avatar.removeClassName("offlineBoxShadow");
            avatar.setClassName("onlineBoxShadow");
            contentLayout.removeClassName("offlineBoxShadow");
            socksLayout.setVisible(false);

            afkBadge.setVisible(avatarMeta.isAfk());
            muteSelfBadge.setVisible(avatarMeta.isMuteSelf());
            vrModeBadge.setVisible(avatarMeta.isInVrMode());
            desktopModeBadge.setVisible(!avatarMeta.isInVrMode());
            supportedAvatarBadge.setVisible(!avatarMeta.isSupportedAvatar());

            expBar.setExpLevel((int) (avatarMeta.getExp() * 0.05));
            expBar.setExpPoints(avatarMeta.getExp() % 20);

            hpBar.setHpBarFull((int) ((avatarMeta.getHp() * 0.05) * 100));

//            AtomicInteger sleepingThreshold = new AtomicInteger(0);
            paramButtons.forEach(btn -> {
                btn.setEnabled(btn.getAvatarParameter().getExpCost().apply(avatarMeta.getExp()) >= 0);
                if (btn.getAvatarParameter().getParam().equals("StyleOutfit")) {
                    btn.setVisible(false);
                }
                avatarMeta.getAvatarParameters().forEach(newParam -> {
                    if (btn.getAvatarParameter().getParam().equals(newParam.getParam()) && btn.getAvatarParameter().getValue().equals(newParam.getValue())) {
                        btn.setEnabled(false);
                        if (btn.getAvatarParameter().getParam().equals("Outfit") &&
                                (btn.getAvatarParameter().getValue().equals(1) ||
                                 btn.getAvatarParameter().getValue().equals(0) ||
                                 btn.getAvatarParameter().getValue().equals(5) ||
                                 btn.getAvatarParameter().getValue().equals(6))) {
                            socksLayout.setVisible(true);
                        }
                    }
                    if (newParam.getParam().equals("StyleOutfit") && btn.getAvatarParameter().getParam().equals(newParam.getParam()) && !btn.getAvatarParameter().getValue().equals(newParam.getValue())) {
                        btn.setVisible(true);
                    }
//                    if (newParam.getParam().equals("GestureRight") && newParam.getValue().equals(7) ||
//                            newParam.getParam().equals("GestureLeft") && newParam.getValue().equals(0)) {
//                        sleepingThreshold.getAndIncrement();
//                    }
                });
            });
//            sleepingBadge.setVisible(avatarMeta.isInVrMode() && !avatarMeta.isAfk() && sleepingThreshold.get() >= (paramButtons.size() * 2));
        } else {
            avatar.removeClassName("onlineBoxShadow");
            avatar.setClassName("offlineBoxShadow");
            contentLayout.setClassName("offlineBoxShadow");

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
