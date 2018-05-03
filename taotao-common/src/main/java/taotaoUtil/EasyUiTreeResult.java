package taotaoUtil;

/**
 * id：节点ID，对加载远程数据很重要。 text：显示节点文本。 state：节点状态，'open' 或
 * 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。 checked：表示该节点是否被选中。 attributes:
 * 被添加到节点的自定义属性。 children: 一个节点数组声明了若干节点。
 * 
 */
public class EasyUiTreeResult {

	Long id;
	String text;
	String state;
	Boolean checked;
	String attributes;
	String children;
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
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
	}
	
}
