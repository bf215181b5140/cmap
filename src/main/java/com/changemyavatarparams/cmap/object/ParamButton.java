package com.changemyavatarparams.cmap.object;

import com.changemyavatarparams.cmap.controller.OscController;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.shared.Registration;

public class ParamButton extends Button {

    protected AvatarParameter avatarParameter;
    protected String displayText;

    public ParamButton(AvatarParameter avatarParameter){
        this.avatarParameter = avatarParameter;
    }

    public ParamButton(AvatarParameter avatarParameter, String displayText){
        super(displayText);
        this.displayText = displayText;
        this.avatarParameter = avatarParameter;
    }

    private void expCostLabel(){
        int buttonExpCost = this.avatarParameter.getExpCost().apply(0);
        if(buttonExpCost < 0){
            getElement().appendChild(new Element("Div"));
        }
    }

    @Override
    public Registration addClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        return super.addClickListener(y -> OscController.messageSend(this.getAvatarParameter(), true));
    }

    public AvatarParameter getAvatarParameter() {
        return avatarParameter;
    }

    public void setAvatarParameter(AvatarParameter avatarParameter) {
        this.avatarParameter = avatarParameter;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}
