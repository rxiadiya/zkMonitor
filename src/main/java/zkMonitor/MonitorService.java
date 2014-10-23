package zkMonitor;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class MonitorService {

	// configurable connection info and retry policy in future 
	public static String connectionString = "127.0.0.1:2181" ;
	private static String ROOT =  "/";
	private CuratorFramework cfw ;
	private RetryPolicy retryPolicy ; 
	private static ZNodeMonitor znodeStat ;
	private static ClusterMonitor clusterStat;
	
	public MonitorService () {
		retryPolicy = new ExponentialBackoffRetry(1000, 3);
		cfw = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
		znodeStat = new ZNodeMonitor();
		clusterStat = new ClusterMonitor();
		cfw.start();
	}
	
	public static void main (String[] args) {
	
		MonitorService perClusterInstance = new MonitorService() ;
		try {
			znodeStat.startMonitorNodes(perClusterInstance.cfw,ROOT);
			clusterStat.startMonitorCluster(perClusterInstance.cfw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
