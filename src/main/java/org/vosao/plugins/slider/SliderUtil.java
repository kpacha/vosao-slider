package org.vosao.plugins.slider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.vosao.entity.PluginEntity;
import org.vosao.entity.helper.PluginHelper;
import org.vosao.entity.helper.PluginParameter;

public class SliderUtil {

    private Map<String, String> sliderAttributes = null;
    private PluginEntity plugin;

    private static final Log logger = LogFactory
	    .getLog(SliderVelocityPlugin.class);

    public SliderUtil(PluginEntity plugin) {
	this.plugin = plugin;
    }

    public Map<String, String> prepareAttributes(String attributesJSON) {
	return parseAttributes(attributesJSON, getSliderAttributes());
    }

    private Map<String, String> parseAttributes(String attributesJSON,
	    Map<String, String> attributes) {
	try {
	    JSONObject obj = new JSONObject(attributesJSON);
	    Iterator<String> attributeIter = obj.keys();
	    while (attributeIter.hasNext()) {
		String attrName = attributeIter.next();
		attributes.put(attrName, obj.getString(attrName));
	    }
	    return attributes;
	} catch (JSONException e) {
	    logger.error("Config atributes parsing problem: " + attributes);
	}
	return null;
    }

    private Map<String, String> getDefaultAttributes() {
	Map<String, String> attributes = new HashMap<String, String>();
	SliderConfig configAttributes = getConfig(plugin);
	attributes.put("structureId", configAttributes.getStructureId());
	attributes.put("width", configAttributes.getWidth());
	attributes.put("maxLength", configAttributes.getMaxLength());
	attributes.put("height", configAttributes.getHeight());
	attributes.put("wait", configAttributes.getWait());
	attributes.put("fade", configAttributes.getFade());
	attributes.put("direction", configAttributes.getDirection());
	attributes.put("showControls", configAttributes.getShowControls());
	attributes.put("showProgress", configAttributes.getShowProgress());
	attributes.put("hoverPause", configAttributes.getHoverPause());
	attributes.put("autoplay", configAttributes.getAutoplay());
	attributes.put("randomize", configAttributes.getRandomize());
	return attributes;
    }

    private SliderConfig getConfig(PluginEntity plugin) {
	Map<String, PluginParameter> params = PluginHelper
		.parseParameters(plugin);
	SliderConfig result = new SliderConfig();
	try {
	    result.setStructureId(params.get("structureId").getValue());
	} catch (Exception e) {
	    logger.error("structureId parameter: " + e.getMessage());
	}
	try {
	    result.setMaxLength(params.get("maxLength").getValueInteger());
	} catch (Exception e) {
	    logger.error("structureId parameter: " + e.getMessage());
	}
	try {
	    result.setWidth(params.get("width").getValue());
	} catch (Exception e) {
	    logger.error("width parameter: " + e.getMessage());
	}
	try {
	    result.setHeight(params.get("height").getValue());
	} catch (Exception e) {
	    logger.error("height parameter: " + e.getMessage());
	}
	try {
	    result.setWait(params.get("wait").getValueInteger());
	} catch (Exception e) {
	    logger.error("wait parameter: " + e.getMessage());
	}
	try {
	    result.setFade(params.get("fade").getValueInteger());
	} catch (Exception e) {
	    logger.error("fade parameter: " + e.getMessage());
	}
	try {
	    result.setDirection(params.get("direction").getValue());
	} catch (Exception e) {
	    logger.error("direction parameter: " + e.getMessage());
	}
	try {
	    result.setShowControls(params.get("showControls").getValueBoolean());
	} catch (Exception e) {
	    logger.error("showControls parameter: " + e.getMessage());
	}
	try {
	    result.setShowProgress(params.get("showProgress").getValueBoolean());
	} catch (Exception e) {
	    logger.error("showProgress parameter: " + e.getMessage());
	}
	try {
	    result.setHoverPause(params.get("hoverPause").getValueBoolean());
	} catch (Exception e) {
	    logger.error("hoverPause parameter: " + e.getMessage());
	}
	try {
	    result.setAutoplay(params.get("autoplay").getValueBoolean());
	} catch (Exception e) {
	    logger.error("autoplay parameter: " + e.getMessage());
	}
	try {
	    result.setRandomize(params.get("randomize").getValueBoolean());
	} catch (Exception e) {
	    logger.error("randomize parameter: " + e.getMessage());
	}
	return result;
    }

    public String getJSONAttributes(Map<String, String> attributes) {
	JSONObject obj = new JSONObject();
	Iterator it = attributes.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry pairs = (Map.Entry) it.next();
	    String key = (String) pairs.getKey();
	    if (isPrivateAttribute(key)) {
		continue;
	    }
	    try {
		obj.put(key, pairs.getValue());
	    } catch (JSONException e) {
		logger.error("Config atributes JSON parsing problem: "
			+ attributes);
	    }
	}
	return obj.toString();
    }

    private boolean isPrivateAttribute(String key) {
	return (key.equalsIgnoreCase("structureId")
		|| key.equalsIgnoreCase("maxLength")
		|| key.equalsIgnoreCase("height") || key
		    .equalsIgnoreCase("width"));
    }

    public Map<String, String> getSliderAttributes() {
	if (sliderAttributes == null) {
	    setSliderAttributes(getDefaultAttributes());
	}
	return sliderAttributes;
    }

    private void setSliderAttributes(Map<String, String> sliderAttributes) {
	this.sliderAttributes = sliderAttributes;
    }

}
