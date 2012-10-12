package org.vosao.plugins.slider;

import org.vosao.business.plugin.AbstractPluginEntryPoint;

public class SliderEntryPoint extends AbstractPluginEntryPoint {

    private SliderVelocityPlugin velocityService;

    @Override
    public Object getPluginVelocityService() {
	if (velocityService == null) {
	    velocityService = new SliderVelocityPlugin(getBusiness());
	}
	return velocityService;
    }

    @Override
    public String getBundleName() {
	return "org.vosao.plugins.slider.messages";
    }
}
