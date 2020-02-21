package com.angazadesign.sukavi;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.angazadesign.sukavi.application.Utility.getRandomQuote;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;


public class GetFortunesTest extends TestCase {
	
	@Test
	public void testRandomQuote() {
		
		List<String> actual = Arrays.asList(
				"a penny saved is a penny earned   ",
				"life is short, smile while you have teeth   ",
				"people say nothing is impossible and I do nothing all day   ",
				"doing nothing is hard, you never know when you're done   ",
				"your life can't fall apart if you never had it together!   "
		);
		
		String expected = getRandomQuote().getFortunes().get(0);
		
		assertThat(actual, hasItems(expected));
	}
	
}