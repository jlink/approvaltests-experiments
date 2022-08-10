package experiments;

import java.util.*;

import com.spun.util.tests.*;
import org.approvaltests.*;
import org.approvaltests.core.*;
import org.approvaltests.namer.*;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.*;

class JqwikApprovals implements PerProperty.Lifecycle {

	private static final Object RESULTS_KEY = Tuple.of(JqwikApprovals.class, "results");
	private static Store<List<String>> results = Store.create(RESULTS_KEY, Lifespan.PROPERTY, ArrayList::new);
	private static StackTraceReflectionResult info;

	static void verify(String value) {
		info = TestUtils.getCurrentFileForMethod(new AttributeStackSelector());
		results.update(results -> {
			results.add(value);
			return results;
		});
	}

	public void onSuccess() {
		List<String> values = Store.<List<String>>get(RESULTS_KEY).get();

		ApprovalNamer namer = new StackTraceNamer(info, ".jqwik");
		Options options = new Options().forFile().withNamer(namer);
		Approvals.verifyAll("results", values, options);
	}

}
