package kt.vo;

public class Host {

	private long hostid;
	private String host;
	private int status;
	private int available;
	private String name;
	private int flags;
	
	public long getHostid() {
		return hostid;
	}
	public void setHostid(long hostid) {
		this.hostid = hostid;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFlags() {
		return flags;
	}
	public void setFlags(int flags) {
		this.flags = flags;
	}
	
}
