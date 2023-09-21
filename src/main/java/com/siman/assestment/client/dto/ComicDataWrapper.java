package com.siman.assestment.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComicDataWrapper extends ObjDataWrapper {
	private ObjDataContainer<Comic> data;
}
