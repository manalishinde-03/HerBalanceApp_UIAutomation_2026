package utilities;

public class TestState {
	
	    // ThreadLocal ensures that if you run 2 tests at once, 
	    // Phase A doesn't overwrite Phase B.
	    private static ThreadLocal<String> currentPhase = new ThreadLocal<>();

	    // Call this in your @Given step
	    public static void setCurrentPhase(String phase) {
	        currentPhase.set(phase);
	    }

	    // Call this in your @Then step
	    public static String getCurrentPhase() {
	        return currentPhase.get();
	    }

	    // Clean up after each test to keep your data fresh
	    public static void reset() {
	        currentPhase.remove();
	    }
}
