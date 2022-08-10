package experiments;

import java.util.*;

import org.approvaltests.*;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import net.jqwik.api.lifecycle.*;

class WithJqwikTests {

	/**
	 * Jqwik examples work just like plain Jupiter tests
	 */
	@Example
	void testTuples() {
		List<Tuple.Tuple2<String, Integer>> tuples = List.of(
			Tuple.of("a", 1),
			Tuple.of("b", 2),
			Tuple.of("c", 3)
		);
		Approvals.verifyAll("tuples", tuples);
	}

	/**
	 * Using this handmade lifecycle will check all values at the end,
	 * and only if the property succeeds otherwise.
	 */
	@Property(tries = 11, seed = "42")
	@PerProperty(JqwikApprovals.class)
	void strings(@ForAll @StringLength(5) @AlphaChars String s) {
		JqwikApprovals.verify(s);
	}

}
