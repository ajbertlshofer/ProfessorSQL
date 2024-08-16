package com.revature.Andrew;
import java.util.List;

/**
 * Created in November of 2023 for my Java I college course, the Ultimate String Tabber is the culimation of my ultimate frustration
 * with how difficult it can be to properly tab-align a table before outputting it to the console. I spent in total 22 weeks in Java I
 * and Java II, and about 16 of those were spent building CLI programs. This is the product of my insanity requiring all of my output
 * to look as good as it did in their examples without hard-coded spacing.
 */
public class Tabber {

    /**
     * Tab-aligns a list of strings with the expectation that they are a column of items
     * 
     * @param stringList Column (list) of strings needing to be tab-aligned
     * @param makeBars Optional decorative bars pre-pended to every item in stringList
     * @return List of strings with required number of tabs appended to align the full column
     */
	public static List<String> tabColumn(List<String> stringList, boolean makeBars) {

		// Optional decorative bars
		if (makeBars) {
			for (int i = 0; i < stringList.size(); i++) {
				stringList.set(i, "| " + stringList.get(i));
			}
		}
		
		// The tab size in my console is 8 characters
		int tabSize = 8;
		int maxSize = 0;
		
		// Find the length of the longest string in the list
		for (String string : stringList) {
			if (string.length() > maxSize) {
				maxSize = string.length();
			}
		}
		// Find the number of tabs needed to reach that length
		int tabLength = maxSize / tabSize;
		for (int i = 0; i < stringList.size(); i++) {
			String currString = stringList.get(i);
			int tabDiff = tabLength - (currString.length() / tabSize);
			for (int x = 0; x < Math.max(0, tabDiff); x++) {
				// Tab string up to the block the longest one is in
				currString += "\t";
			}
			// Add one more tab so everything aligns
			currString += "\t";
			stringList.set(i, currString);
		}
		
		return stringList;
		
	}

}
