package com.wildcards;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


public class Wildcards {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String directory = "D:\\parent\\*\\F22";
		String directory = args[0].toString();
		String[] pathSplit = directory.split("\\\\");			
		List<String> directories = new ArrayList<String>();
		boolean skipHidden = true;
		//get the complete list of directories with processed wildcards
		_getDirectoryList(pathSplit[0], 1, pathSplit, directories, skipHidden);
		for(String dir:directories){
			System.out.println(dir);
		}

	}
	public static void _getDirectoryList(String path, int i, String[] split,
			List<String> directories, boolean skipHidden) {
		if (path != null) {

			if (i == split.length) {
				File file = new File(path);
				if (file.exists()) {
					directories.add(path);
				}

			} else {
				if ("*".equals(split[i]) || split[i].contains("?")) {  //if it is a wildcard
					String[] dir = getDirectories(path);
					if(dir == null) return;
					for (int j = 0; j < dir.length; j++) {
						if ("*".equals(split[i])
								|| (split[i].contains("?") && split[i].length() == dir[j].length()))
							_getDirectoryList(buildPath(path, dir[j], skipHidden), i + 1, split,
									directories, skipHidden);
					}
				} else {
					_getDirectoryList(buildPath(path, split[i], skipHidden), i + 1, split,
							directories, skipHidden);
				}
			}
		}
	}

	/**
	 * Builds a simple path using String concatenation and return the path if it is valid 
	 *
	 */
	public static String buildPath(String path, String folder, boolean skipHidden) {
		path = path + ("".equals(path) ? null : "\\") + folder;
		File file = new File(path);
		if (file.exists() && !(skipHidden && file.isHidden())) { // to check the case if the file is hidden but hidden files need to be skipped
			return path;
		}
		return null;
	}

	/**
	 * Return a String array consisting of first level directories within a directory path
	 * @param path
	 * @return
	 */
	public static String[] getDirectories(String path) {
		if(path.endsWith(":"))
			path = path + "\\";
		File file = new File(path);

		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});
		return directories;
	}


}
