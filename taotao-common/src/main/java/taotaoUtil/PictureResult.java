package taotaoUtil;

public class PictureResult {

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	String url;
	Integer error;
	
	public PictureResult(){
		
	}
	public PictureResult(Integer error,String url){
		this.url = url;
		this.error = error;
	}
}
