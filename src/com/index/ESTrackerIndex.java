package com.index;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.shield.ShieldPlugin;
import com.beans.UserIndexRecordBuilder;
import com.constants.ESTrackerIndexConstants;

public class ESTrackerIndex {
	public static Client client;
	private static BulkRequestBuilder bulkRequest;
	
	public ESTrackerIndex(){
		try{
			client = TransportClient.builder()/*.addPlugin(ShieldPlugin.class)*/
					.settings(Settings.builder().put("cluster.name", ESTrackerIndexConstants.CLUSER_NAME)).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
					
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
	}
	
	public void addUserTrackInfo(String ipAddress, String latitude, String longitude, String time){
		
		UserIndexRecordBuilder indexRecord;
		indexRecord = new UserIndexRecordBuilder(ipAddress, latitude, longitude, time, ipAddress+"_"+latitude+"_"+longitude+"_"+time);
		try{
			insertIndexRecord(ESTrackerIndexConstants.INDEX_NAME, ESTrackerIndexConstants.INDEX_TYPE, indexRecord);
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			client.close();
		}		
	}
	
	public void insertIndexRecord(String indexName, String indexType, UserIndexRecordBuilder indexRecord) throws IOException{
		IndexRequestBuilder indexRequestBuilder = client.prepareIndex(indexName, indexType);
		XContentBuilder contentBuilder;
		contentBuilder = XContentFactory.jsonBuilder().startObject().prettyPrint();
		contentBuilder.field(ESTrackerIndexConstants.IP_ADDRESS, indexRecord.getIpAddress());
		contentBuilder.field(ESTrackerIndexConstants.LONGITUDE, indexRecord.getLongitude());
		contentBuilder.field(ESTrackerIndexConstants.LATITUDE, indexRecord.getLatitude());
		contentBuilder.field(ESTrackerIndexConstants.TIME, indexRecord.getTime());
		contentBuilder.endObject();
		indexRequestBuilder.setSource(contentBuilder).setId(indexRecord.getId());
		IndexResponse response = indexRequestBuilder.execute().actionGet();
		System.out.println("Result of inserting index record " + response);
	}
	
}
