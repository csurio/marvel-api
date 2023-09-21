package com.siman.assestment.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjDataWrapper {
	private int                 code;
	private String              status;
	private String              copyright;
	private String              attributionText;
	private String              attributionHTML;
//	private ObjDataContainer<T> data;
	private String              etag;

}
