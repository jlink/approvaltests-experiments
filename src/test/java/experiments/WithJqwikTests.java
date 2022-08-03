package experiments;

import java.util.*;

import org.approvaltests.Approvals;
import net.jqwik.api.*;

class WithJqwikTests {

	@Example
	void testTuples() {
		Set<Tuple.Tuple2<String, Integer>> tuples = Set.of(
			Tuple.of("a", 1),
			Tuple.of("b", 2),
			Tuple.of("c", 3)
		);
		Approvals.verifyAll("tuples", tuples);
	}

}
