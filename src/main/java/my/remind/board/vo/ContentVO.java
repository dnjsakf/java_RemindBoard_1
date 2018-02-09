package my.remind.board.vo;

public class ContentVO {
	
	private int cNo;
	private String cTitle;
	private String cWriter;
	private String cDate;
	private String cContent;
	private int cAvailable = 1;
	
	public int getcNo() {
		return cNo;
	}
	public String getcTitle() {
		return cTitle;
	}
	public String getcWriter() {
		return cWriter;
	}
	public String getcDate() {
		return cDate;
	}
	public String getcContent() {
		return cContent;
	}
	public int getcAvailable() {
		return cAvailable;
	}
	public void setcNo(int cNo) {
		this.cNo = cNo;
	}
	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}
	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public void setcAvailable(int cAvailable) {
		this.cAvailable = cAvailable;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n[content]");
		sb.append("\ncNO: " + this.cNo);
		sb.append("\ncTitle: " + this.cTitle);
		sb.append("\ncWriter: " + this.cWriter);
		sb.append("\ncDate: " + this.cDate);
		sb.append("\ncContent: " + this.cContent);
		sb.append("\ncAvailable: " + this.cAvailable);
		return sb.toString();
	}
}
