package bestnode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BestNodeSelector {

	public static void main(String[] args) {
		// node list url
		String nodeListUrl = "https://nodes.iota.works/api/ssl/live";

		try {
			NodeModel bestNode = getBestNode(nodeListUrl);
			System.out.println(bestNode.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the best node in terms of parameters pow, milestone, health and
	 * neighbors
	 * 
	 * @return best node (NodeModel)
	 * @throws Exception
	 */
	public static NodeModel getBestNode(String nodeListUrl) throws Exception {
		List<NodeModel> nodesList = new LinkedList<NodeModel>();
		NodeModel bestNode = null;

		String json = readUrl(nodeListUrl);
		String jsonContent = "{\"nodes\":" + json + "}";
		JSONObject jsonObject = new JSONObject(jsonContent);
		JSONArray arr = jsonObject.getJSONArray("nodes");

		for (int i = 0; i < arr.length(); i++) {
			String nodeUrl = arr.getJSONObject(i).getString("node");
			int health = arr.getJSONObject(i).getInt("health");
			int neighbors = arr.getJSONObject(i).getInt("neighbors");
			String version = arr.getJSONObject(i).getString("version");
			int milestone = arr.getJSONObject(i).getInt("milestone");
			int load = arr.getJSONObject(i).getInt("load");
			boolean pow = arr.getJSONObject(i).getBoolean("pow");
			boolean spent = arr.getJSONObject(i).getBoolean("spent");

			// validates if url consists out of an protocol, host and port;
			String[] nodeArray = nodeUrl.split(":");
			if (nodeArray.length == 3) {
				String protocol = nodeArray[0];
				String host = nodeArray[1].replaceAll("//", "");
				String port = nodeArray[2];
				NodeModel newNode = new NodeModel(protocol, host, port, health, neighbors, version, milestone, load,
						pow, spent);
				nodesList.add(newNode);
			}
		}

		int bestMilestone = 0;
		int bestHealth = 0;
		int bestNeighbors = 0;

		for (NodeModel aNode : nodesList) {

			// check if node supports pow; continue only if true
			if (aNode.isPow()) {
				if (aNode.getMilestone() > bestMilestone) {
					bestMilestone = aNode.getMilestone();
					bestHealth = aNode.getHealth();
					bestNeighbors = aNode.getNeighbors();
					bestNode = aNode;
				} else if (aNode.getMilestone() == bestMilestone) {
					if (aNode.getHealth() > bestHealth) {
						bestHealth = aNode.getHealth();
						bestNeighbors = aNode.getNeighbors();
						bestNode = aNode;
					} else if (aNode.getHealth() == bestHealth) {
						if (aNode.getNeighbors() > bestNeighbors) {
							bestNeighbors = aNode.getNeighbors();
							bestNode = aNode;
						}
					}
				}
			}

		}
		return bestNode;
	}

	/**
	 * Reads content of json url
	 * 
	 * @param urlString
	 * @return content
	 * @throws Exception
	 */
	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

}
