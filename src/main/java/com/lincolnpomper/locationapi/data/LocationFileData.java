package com.lincolnpomper.locationapi.data;

import java.util.Arrays;

public class LocationFileData {

	public String name;
	public String[] values;

	@Override
	public String toString() {
		return "LocationFileData [name=" + name + ", values=" + Arrays.toString(values) + "]";
	}
}