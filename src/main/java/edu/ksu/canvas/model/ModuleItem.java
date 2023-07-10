package edu.ksu.canvas.model;

import java.io.Serializable;

/* https://canvas.instructure.com/doc/api/modules.html#ModuleItem */
public class ModuleItem extends BaseCanvasModel implements Serializable {
	private Long id;
	private Long moduleId;
	private Long position;
	private String title;
	private Long indent;
	private String type;
	private Long contentId;
	private String htmlUrl;
	private String url; /* optional */
	private String pageUrl; /* only for 'Page' type */
	private String externalUrl; /* only for 'ExternalUrl' and 'ExternalTool' types */
	private Boolean newTab; /* only for 'ExternalTool' type */
	private Boolean published;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getIndent() {
		return indent;
	}

	public void setIndent(Long indent) {
		this.indent = indent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getContentId() {
		return contentId;
	}

	public void setContenId(long contentId) {
		this.contentId = contentId;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	public java.lang.Boolean getNewTab() {
		return newTab;
	}

	public void setNewTab(java.lang.Boolean newTab) {
		this.newTab = newTab;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
}