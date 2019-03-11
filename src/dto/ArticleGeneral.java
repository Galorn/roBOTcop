package dto;

public class ArticleGeneral {

	private String link;
	private String jpgLink;
	private String altName;
	private boolean isSoldOut;
	
	public ArticleGeneral(String link, String jpgLink, String altName, boolean isSoldOut) {
		this.link = link;
		this.jpgLink = jpgLink;
		this.altName = altName;
		this.isSoldOut = isSoldOut;
	}
	public ArticleGeneral() {
		// TODO Auto-generated constructor stub
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getJpgLink() {
		return jpgLink;
	}
	public void setJpgLink(String jpgLink) {
		this.jpgLink = jpgLink;
	}
	public String getAltName() {
		return altName;
	}
	public void setAltName(String altName) {
		this.altName = altName;
	}
	public boolean isSoldOut() {
		return isSoldOut;
	}
	public void setSoldOut(boolean isSoldOut) {
		this.isSoldOut = isSoldOut;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altName == null) ? 0 : altName.hashCode());
		result = prime * result + (isSoldOut ? 1231 : 1237);
		result = prime * result + ((jpgLink == null) ? 0 : jpgLink.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleGeneral other = (ArticleGeneral) obj;
		if (altName == null) {
			if (other.altName != null)
				return false;
		} else if (!altName.equals(other.altName))
			return false;
		if (isSoldOut != other.isSoldOut)
			return false;
		if (jpgLink == null) {
			if (other.jpgLink != null)
				return false;
		} else if (!jpgLink.equals(other.jpgLink))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ArticleGeneral [link=" + link + ", jpgLink=" + jpgLink + ", altName=" + altName + ", isSoldOut="
				+ isSoldOut + "]";
	}
	
	
}
