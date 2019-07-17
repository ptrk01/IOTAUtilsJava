package bestnode;

public class NodeModel {

	private String protocol;
	private String host;
	private String port;
	private int health;
	private int neighbors;
	private String version;
	private int milestone;
	private int load;
	private boolean pow;
	private boolean spent;

	public NodeModel(String protocol, String host, String port, int health, int neighbors, String version,
			int milestone, int load, boolean pow, boolean spent) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.health = health;
		this.neighbors = neighbors;
		this.version = version;
		this.milestone = milestone;
		this.load = load;
		this.pow = pow;
		this.spent = spent;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(int neightbors) {
		this.neighbors = neightbors;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getMilestone() {
		return milestone;
	}

	public void setMilestone(int milestone) {
		this.milestone = milestone;
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public boolean isPow() {
		return pow;
	}

	public void setPow(boolean pow) {
		this.pow = pow;
	}

	public boolean isSpent() {
		return spent;
	}

	public void setSpent(boolean spent) {
		this.spent = spent;
	}

	@Override
	public String toString() {
		return "Node [protocol=" + protocol + ", host=" + host + ", port=" + port + ", health=" + health
				+ ", neightbors=" + neighbors + ", version=" + version + ", milestone=" + milestone + ", load=" + load
				+ ", pow=" + pow + ", spent=" + spent + "]";
	}

}
