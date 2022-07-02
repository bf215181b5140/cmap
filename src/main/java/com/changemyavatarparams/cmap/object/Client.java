package com.changemyavatarparams.cmap.object;

import com.changemyavatarparams.cmap.view.ChangeMyAvatarParams;
import com.vaadin.flow.component.UI;

public class Client {
    private UI ui;
    private ChangeMyAvatarParams view;

    public Client(UI ui, ChangeMyAvatarParams view) {
        this.ui = ui;
        this.view = view;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public ChangeMyAvatarParams getView() {
        return view;
    }

    public void setView(ChangeMyAvatarParams view) {
        this.view = view;
    }

    public String toString() {
        return "UI: " + ui.toString() + " View: " + view.toString();
    }
}
