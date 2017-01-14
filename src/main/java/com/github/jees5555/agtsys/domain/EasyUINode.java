package com.github.jees5555.agtsys.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EasyUINode {
	// id：节点ID，对加载远程数据很重要。
	private Long id;
	// text：显示节点文本。
	private String text;
	// state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
	private String state = "open";
	// checked：表示该节点是否被选中。
	private boolean checked;
	// attributes: 被添加到节点的自定义属性。
	private Map<String, Object> attributes;
	// children: 一个节点数组声明了若干节点。
	private List<EasyUINode> children = new ArrayList<EasyUINode>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<EasyUINode> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUINode> children) {
		this.children = children;
	}

}