package com.index;

import java.util.Iterator;

import com.beans.JSONMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class CreateJSON {
	public void create(JSONMapper object){
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();
		
		arrayNode.add(mapper.convertValue(object, JsonNode.class));
		
		for(JsonNode obj: arrayNode){	
			Iterator<String> fields = obj.fieldNames();
			
			while (fields.hasNext()) {
				String field = fields.next();
				System.out.println(field + ": " + obj.get(field));
			}
		}
		
		System.out.println(arrayNode);
	}
}
