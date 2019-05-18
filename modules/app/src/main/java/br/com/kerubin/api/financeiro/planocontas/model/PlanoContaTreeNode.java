package br.com.kerubin.api.financeiro.planocontas.model;

import java.util.ArrayList;
import java.util.List;

public class PlanoContaTreeNode {
	
	private String key;
	private String label;
	private String data;
	private String expandedIcon;
	private String collapsedIcon;
	
	private List<PlanoContaTreeNode> children = new ArrayList<>();
	
	public PlanoContaTreeNode() {
		expandedIcon = "fa fa-folder-open";
		collapsedIcon = "fa fa-folder";
	}
	
	protected String buildLabel(String label, String data) {
		return label + " - " + data;
	}
	
	public PlanoContaTreeNode(String key, String label) {
		this();
		this.key = key;
		this.label = label;
	}
	
	public PlanoContaTreeNode(String key, String label, String data) {
		this();
		this.key = key;
		// this.label = buildLabel(label, data);
		this.label = label;
		this.data = data;
	}
	
	public PlanoContaTreeNode(String key, String label, String data, String expandedIcon, String collapsedIcon) {
		this.key = key;
		//this.label = buildLabel(label, data);
		this.label = label;
		this.data = data;
		this.expandedIcon = expandedIcon;
		this.collapsedIcon = collapsedIcon;
	}
	
	public PlanoContaTreeNode addNode(String key, String label, String data, String expandedIcon, String collapsedIcon) {
		PlanoContaTreeNode node = new PlanoContaTreeNode(key, label, data, expandedIcon, collapsedIcon);
		addNode(node);
		return this;
	}
	
	public PlanoContaTreeNode addNode(String key, String label, String data) {
		PlanoContaTreeNode node = new PlanoContaTreeNode(key, label, data);
		addNode(node);
		return this;
	}
	
	public PlanoContaTreeNode addNode(PlanoContaTreeNode node) {
		children.add(node);
		return this;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getExpandedIcon() {
		return expandedIcon;
	}

	public void setExpandedIcon(String expandedIcon) {
		this.expandedIcon = expandedIcon;
	}

	public String getCollapsedIcon() {
		return collapsedIcon;
	}

	public void setCollapsedIcon(String collapsedIcon) {
		this.collapsedIcon = collapsedIcon;
	}

	public List<PlanoContaTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<PlanoContaTreeNode> children) {
		this.children = children;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
