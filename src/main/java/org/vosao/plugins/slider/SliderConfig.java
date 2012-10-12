package org.vosao.plugins.slider;

public class SliderConfig {

    private String structureId;
    private int maxLength;
    private String width;
    private String height;
    private int wait;
    private int fade;
    private String direction;
    private boolean showControls;
    private boolean showProgress;
    private boolean hoverPause;
    private boolean autoplay;
    private boolean randomize;

    public SliderConfig() {
    }

    public String getStructureId() {
	return structureId;
    }

    public void setStructureId(String structureId) {
	this.structureId = structureId;
    }

    public String getMaxLength() {
	return Integer.toString(maxLength);
    }

    public void setMaxLength(int maxLength) {
	this.maxLength = maxLength;
    }

    public String getWidth() {
	return width;
    }

    public void setWidth(String width) {
	this.width = width;
    }

    public String getHeight() {
	return height;
    }

    public void setHeight(String height) {
	this.height = height;
    }

    public String getWait() {
	return Integer.toString(wait);
    }

    public void setWait(int wait) {
	this.wait = wait;
    }

    public String getFade() {
	return Integer.toString(fade);
    }

    public void setFade(int fade) {
	this.fade = fade;
    }

    public String getDirection() {
	return direction;
    }

    public void setDirection(String direction) {
	this.direction = direction;
    }

    public String getShowControls() {
	return (showControls) ? "true" : "false";
    }

    public void setShowControls(boolean showControls) {
	this.showControls = showControls;
    }

    public String getShowProgress() {
	return (showProgress) ? "true" : "false";
    }

    public void setShowProgress(boolean showProgress) {
	this.showProgress = showProgress;
    }

    public String getHoverPause() {
	return (hoverPause) ? "true" : "false";
    }

    public void setHoverPause(boolean hoverPause) {
	this.hoverPause = hoverPause;
    }

    public String getAutoplay() {
	return (autoplay) ? "true" : "false";
    }

    public void setAutoplay(boolean autoplay) {
	this.autoplay = autoplay;
    }

    public String getRandomize() {
	return (randomize) ? "true" : "false";
    }

    public void setRandomize(boolean randomize) {
	this.randomize = randomize;
    }

}
