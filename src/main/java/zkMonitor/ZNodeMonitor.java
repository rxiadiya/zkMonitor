package zkMonitor;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;

public class ZNodeMonitor {
	
	public ZNodeMonitor() {}

	public void startMonitorNodes(CuratorFramework cfw, String path) {
		try {
			List<String> allNodes = cfw.getChildren().forPath(path);
			for (String node : allNodes) {
				String nodeName =  cfw.getNamespace();
				byte[] datas = cfw.getData().forPath(node);
				System.out.println( " basic info sharing : " + nodeName) ;
				System.out.println(" get Stat Object of each of the Node" + datas);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
