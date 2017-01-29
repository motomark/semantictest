package sematicstest;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 * Java is Pass-by-value. Uses Copy Semantics.
 * 
 * @author mark
 *
 */
public class TestDogSemantics {

	Dog a;
	Dog b;

	@org.junit.Before
	public void beforeTest() {
		a = new Dog("tommy");
		b = new Dog("patch");

	}

	/**
	 * Test the copy semantics to prove we can modify the original object passed
	 * in because the parameter is a copy of the reference pointer so we change
	 * the dog name of the object referred to..
	 */
	@Test
	public void testChangeNameSemantics() {

		Dog d = new Dog("fido");
		assertEquals("fido", d.getName());

		// changeName1 succeeds.
		changeName1(d);
		assertEquals("butch", d.getName());

		// changeName2 tries to change by re-assigning d to a new Dog, but
		// cannot because d is a new reference, the original copy is preserved.
		changeName2(d);
		assertEquals("butch", d.getName());
	}

	/**
	 * Unable to swap a for b because a java method doesn't allow. Pass by copy.
	 * 
	 */
	@Test
	public void testCannotSwapDogs() {

		assertEquals("tommy", a.getName());
		assertEquals("patch", b.getName());

		// Hope to swap objects, but can't because Java is pass by copy.
		swapDogs(a, b);

		Assert.assertNotEquals("patch", a.getName());
		Assert.assertNotEquals("tommy", b.getName());

	}

	/**
	 * How to swap by passing an Array.
	 * 
	 */
	@Test
	public void testCanSwapDogsViaArray() {

		Dog[] dogs = new Dog[2];
		dogs[0] = a;
		dogs[1] = b;

		// Assert the original dogs.
		Assert.assertEquals("tommy", dogs[0].getName());
		Assert.assertEquals("patch", dogs[1].getName());

		swapDogs(dogs);

		// Assert after we have swapped.
		Assert.assertEquals("patch", dogs[0].getName());
		Assert.assertEquals("tommy", dogs[1].getName());

	}

	/**
	 * The parameter is 'Copy' of the Pointer reference. So what is being
	 * referenced can change the name attribute.
	 * 
	 * @param dog
	 */
	private void changeName1(Dog dog) {
		dog.setName("butch");

	}

	/**
	 * The parameter is 'Copy' of the Pointer reference. The copy is now
	 * re-assigned a new dog object. Actually at a different location. Cannot
	 * change the original reference.
	 * 
	 * @param dog
	 */
	private void changeName2(Dog dog) {
		dog = new Dog("rex");

	}

	/**
	 * 
	 * This doesn't work. Java is not pass-by reference. The parameters are
	 * passed by copy so a and b are both copies of the reference.
	 * 
	 * @param a
	 *            Dog
	 * @param b
	 *            Dog
	 */
	private void swapDogs(Dog a, Dog b) {

		// a and b are copies, do not directly reference the callee reference.
		Dog tempDog = a;
		a = b;
		b = tempDog;
	}

	/**
	 * This will work because the copy of the array reference never tries
	 * re-assignment, however the array elements can be re-assigned (moved).
	 * 
	 * @param dogs
	 */
	private void swapDogs(Dog[] dogs) {
		Dog temp = dogs[0];
		dogs[0] = dogs[1];
		dogs[1] = temp;
	}

}
