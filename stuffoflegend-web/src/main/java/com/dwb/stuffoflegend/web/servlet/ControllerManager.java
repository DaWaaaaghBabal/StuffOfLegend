package com.dwb.stuffoflegend.web.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ControllerManager {
	
	private Map<String, Class<? extends Controller>> mappings = new HashMap<>();
	
	public Controller getController(String urlPattern) {
		if(mappings.isEmpty()) {
			fetchControllers();
		}
		try {
			return mappings.get(urlPattern).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void fetchControllers() {
		String rootPath = ControllerManager.class.getResource("/").getFile();
		File packageDirectory = new File(rootPath);
		List<File> classFiles = extractClassFiles(packageDirectory);
		
		for(File file : classFiles) {
			String className=file.getPath()
					.substring(rootPath.length()-1)
					.replaceAll("\\.class", "")
					.replaceAll("\\\\", ".");
			try {
				Class<?> clazz = Class.forName(className);
				if (Controller.class.isAssignableFrom(clazz)) {
					UrlMapping mapping = clazz.getAnnotation(UrlMapping.class);
					if (mapping != null) {
						mappings.put(mapping.pattern(), (Class<? extends Controller>) clazz);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private List<File> extractClassFiles(File packageDirectory) {
		List<File> files = new LinkedList<File>();
		for (File file : packageDirectory.listFiles()){
			if(file.isDirectory()) {
				files.addAll(extractClassFiles(file));
			}
			else {
				String ext = getExtension(file.getPath());
				if ("class".equals(ext)){
					files.add(file);
				}
			}
		}
		return files;
	}
	
	private static String getExtension(String path) {
		String[] splitPath = path.split("\\\\|/");
		String fileName = splitPath[splitPath.length-1];
		return fileName.split("\\.")[1];
	}
}
