package com.changemyavatarparams.cmap;

import com.changemyavatarparams.cmap.controller.OscController;
import com.changemyavatarparams.cmap.object.AvatarParameter;
import com.changemyavatarparams.cmap.view.ChangeMyAvatarParams;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@Theme(value="cmap", variant = Lumo.LIGHT)
@Push
@SpringBootApplication
public class CmapApplication extends SpringBootServletInitializer implements AppShellConfigurator {

	// mvn clean package -P production

	public static void main(String[] args) {

		OscController.messageRecieve();

		SpringApplication.run(CmapApplication.class, args);

		new Thread(() -> {
			while (true){
				try {
					Thread.sleep(100000);
				} catch (Exception e) {
					System.out.println(java.time.LocalDateTime.now() + " Failed to sleep. Exception: " + e.getMessage());
				} finally {
					AvatarParameter pulseParam = new AvatarParameter("VRMode", 10);
					if(OscController.avatarMeta.getExp() < 20) {
						pulseParam = new AvatarParameter("exp", 20);
					}
					try {
						OscController.messageSend(pulseParam, false);
					} catch (Exception e) {
						System.out.println(java.time.LocalDateTime.now() + " Failed to send out OSC pulse message for VRMode [10]. Exception: " + e.getMessage());
					}
					if (OscController.onlineCheck()) {
						OscController.sendParams();
					}
				}

			}
		}).start();

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(ChangeMyAvatarParams.class);
	}

}
