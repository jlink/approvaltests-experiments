package experiments;

import java.util.*;

import org.approvaltests.*;
import org.approvaltests.combinations.*;
import org.junit.jupiter.api.*;

class WithJupiterTests {

	@Test
	void testString() {
		Approvals.verify("hello approvals");
	}

	@Test
	void testList() {
		String[] names = {"Llewellyn", "James", "Dan", "Jason", "Katrina"};
		Arrays.sort(names);
		Approvals.verifyAll("", names);
	}

	@Test
	void combinatorics() {
		CombinationApprovals.verifyAllCombinations(
			(a, b) -> a + "=" + b,
			new String[]{"a", "b", "c"},
			new String[]{"1", "2", "3"}
		);
	}
}
