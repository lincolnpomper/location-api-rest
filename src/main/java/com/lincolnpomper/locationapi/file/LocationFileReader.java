package com.lincolnpomper.locationapi.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationFileReader {

	private static final String SEPARATOR = ",";
	private static LocationFileReader me;

	private Map<ResourceType, List<LocationFileData>> resources = new HashMap<>();
	private BufferedReader reader = null;

	public enum ResourceType {

		LocationOfInterest("base_pois_def.csv"),
		VehiclePositions("posicoes.csv");

		private String value;

		ResourceType(String value) {
			this.value = value;
		}

		String getValue() {
			return value;
		}
	}

	private LocationFileReader() {
	}

	public static LocationFileReader getInstance() {
		if (me == null) {
			me = new LocationFileReader();
			me.parseCSV();
		}
		return me;
	}

	private void parseCSV() {

		for (ResourceType type : ResourceType.values()) {
			resources.put(type, parseCSVFromFile(type.getValue()));
		}
	}

	private List<LocationFileData> parseCSVFromFile(String fileName) {

		List<LocationFileData> list = new ArrayList<>();

		try {

			reader = findFileFromEnvironment(fileName);
			String line;

			// skip header
			reader.readLine();

			while ((line = reader.readLine()) != null) {

				String[] array = line.split(SEPARATOR, -1);

				for (int i = 0; i < array.length; i++) {
					if ("".equals(array[i])) {
						array[i] = null;
					}
				}

				String[] values = new String[array.length - 1];

				String name = array[0];
				System.arraycopy(array, 1, values, 0, array.length - 1);

				LocationFileData item = new LocationFileData();
				item.name = name;
				item.values = values;

				list.add(item);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	private BufferedReader findFileFromEnvironment(String fileName) {

		BufferedReader reader = null;

		try {
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory()) {
				reader = new BufferedReader(new FileReader(fileName));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (reader == null) {
			InputStream in = getClass().getResourceAsStream(File.separator + fileName);
			reader = new BufferedReader(new InputStreamReader(in));
		}

		return reader;
	}

	public List<LocationFileData> getList(ResourceType key) {
		return resources.get(key);
	}
}