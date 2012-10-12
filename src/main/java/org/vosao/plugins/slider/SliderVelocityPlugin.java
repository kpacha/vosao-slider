package org.vosao.plugins.slider;

import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.vosao.business.Business;
import org.vosao.utils.StreamUtil;
import org.vosao.velocity.plugin.AbstractVelocityPlugin;

public class SliderVelocityPlugin extends AbstractVelocityPlugin {
    private SliderUtil util = null;

    public SliderVelocityPlugin(Business business) {
	setBusiness(business);
    }

    private String renderSlider(String templateUrl, String parentUrl,
	    String sliderId, String attributesJSON, String structureId) {
	try {
	    Map<String, String> attributes = getUtil().prepareAttributes(
		    attributesJSON);
	    String template = StreamUtil.getTextResource(
		    SliderVelocityPlugin.class.getClassLoader(), templateUrl);
	    VelocityContext context = new VelocityContext();
	    getBusiness().getPageBusiness().addVelocityTools(context);
	    context.put("parentUrl", parentUrl);
	    context.put("sliderId", sliderId);
	    context.put("maxLength",
		    Integer.parseInt(attributes.get("width")) - 1);
	    context.put("structureId", structureId);
	    context.put("options", getUtil().getJSONAttributes(attributes));
	    context.put("width", attributes.get("width"));
	    context.put("height", attributes.get("height"));
	    context.put("service", getBusiness().getPageBusiness()
		    .getVelocityService());
	    context.put("siteConfig", getDao().getConfigDao().getConfig());
	    context.put("languageCode", getBusiness().getLanguage());
	    return getBusiness().getSystemService().render(template, context);
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}
    }

    public String renderStructured(String parentUrl, String sliderId,
	    String options, String structureId) {
	return renderSlider("org/vosao/plugins/slider/structuredslider.vm",
		parentUrl, sliderId, options, structureId);
    }

    public String renderStructured(String parentUrl, String sliderId,
	    String options) {
	return renderStructured(parentUrl, sliderId, options,
		getDefaultStructureId());
    }

    public String renderStructured(String parentUrl, String sliderId) {
	return render(parentUrl, sliderId, "{}");
    }

    public String render(String parentUrl, String sliderId, String options) {
	return renderSlider("org/vosao/plugins/slider/rawslider.vm", parentUrl,
		sliderId, options, getDefaultStructureId());
    }

    public String render(String parentUrl, String sliderId) {
	return render(parentUrl, sliderId, "{}");
    }

    private String getDefaultStructureId() {
	return getUtil().getSliderAttributes().get("structureId");
    }

    private SliderUtil getUtil() {
	if (util == null) {
	    setUtil(new SliderUtil(getDao().getPluginDao().getByName("slider")));
	}
	return util;
    }

    private void setUtil(SliderUtil util) {
	this.util = util;
    }
}
