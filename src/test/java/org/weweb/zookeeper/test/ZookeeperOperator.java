package org.weweb.zookeeper.test;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.util.List;

/**
 * Created by wshen on 2017/2/22.
 */
public class ZookeeperOperator extends AbstractZookeeper {
	public void create(String path, byte[] data) throws KeeperException, InterruptedException {
		this.zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	public void getChild(String path) throws KeeperException, InterruptedException {
		List<String> list = this.zooKeeper.getChildren(path, false);
		if (list.isEmpty()) {
			System.out.println(path + "中没有节点");




		} else {
			System.out.println(path + "中存在节点");
			for (String child : list) {
				System.out.println("节点为：" + child);
			}
		}
	}

	public byte[] getData(String path) throws KeeperException, InterruptedException {
		return this.zooKeeper.getData(path, false, null);
	}

	public static void main(String[] args) {
		try {
			ZookeeperOperator zookeeperOperator = new ZookeeperOperator();
			String ip1="192.168.85.101";
			String ip2="192.168.85.102";
			String ip3="192.168.85.103";

			zookeeperOperator.connect(ip1+","+ip2+","+ip3);
			byte[] data = new byte[] { 'a', 'b', 'c', 'd' };
			String zktest = "Zookeeper的Java API测试";
			//zookeeperOperator.create("/root", zktest.getBytes());
			System.out.println("获取设置的信息：" + new String(zookeeperOperator.getData("/root")));
			System.out.println("节点孩子信息：");
			zookeeperOperator.getChild("/root");
			zookeeperOperator.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
