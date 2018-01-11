package version_manager;

import java.util.Comparator;

public class VersionComparator implements Comparator<String> {
	
	/**
	 * Compare two versions dot separated (as 1.1, 1.0.2.1)
	 * The left digits are considered the most significant.
	 * @return 
	 * <ul>
	 * 	<li>0 if the versions are equal</li>
	 * 	<li>-1 if {@code version1} is older than {@code version2}</li>
	 * 	<li>1 if {@code version1} is newer than {@code version2}</li>
	 * </ul>
	 */
	public int compare(String version1, String version2) {
		
		if (version1.equals(version2))
			return 0;
		
		String[] current = version1.split("\\.");
		String[] latest = version2.split("\\.");
		
		int compare = 0;
		for (int i = 0; i < Math.min(latest.length, current.length); ++i) {
			
			// if a number is not found, the default is 0
			int currentNum = i < current.length ? Integer.parseInt(current[i]) : 0;
			int latestNum = i < latest.length ? Integer.parseInt(latest[i]) : 0;
			
			if (currentNum == latestNum)
				continue;
			
			if (currentNum < latestNum) {
				compare = -1;
				break;
			}
			else {
				compare = 1;
				break;
			}
		}
		
		// if we have a older version ask for downloading the last
		return compare;
	}
}
