package caesarcipher;

import java.util.Scanner;

/**
 * 
 * A class to encrypt text using Caesar cipher, also known as shift cipher
 * 
 * Caesar cipher is a type of substitution cipher 
 * in which each letter in the plaintext is replaced 
 * by a letter some fixed number of positions down the alphabet.
 * 
 * For example the plaintext 
 * "in which each letter in the plaintext is replaced", 
 * shifted 5 letters down the alphabet, becomes  
 * "ns bmnhm jfhm qjyyjw ns ymj uqfnsyjcy nx wjuqfhji"
 * 
 * 
 * @author Martin Vatshelle, Anna Eilertsen
 *
 */
public class CeasarCipher {
	static int num_chars = 26;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Dette programmet kan kryptere og dekryptere strenger ved hjelp av CaesarCipher.");
		System.out.println("Skriv \"encrypt\" dersom du vil kryptere en streng og \"decrypt\" dersom du vil de-kryptere en streng.");
		String xCrypt = scanner.nextLine();
		if(xCrypt.equals("encrypt")) {
			System.out.print("Skriv inn en streng du vil kryptere: ");
			String encrypt = scanner.nextLine();
			System.out.print("Skriv inn antall steg krypteringen skal bruke: ");
			String sNum = scanner.nextLine();
			int steps = Integer.parseInt(sNum);
			System.out.println(encrypt(encrypt, steps));
		}
		else if(xCrypt.equals("decrypt")) {
			System.out.print("Skriv inn en streng du vil dekryptere: ");
			String decrypt = scanner.nextLine();
			System.out.print("Skriv inn antall steg dekrypteringen skal bruke: ");
			String sNum = scanner.nextLine();
			int steps = Integer.parseInt(sNum);
			System.out.println(decrypt(decrypt, steps));
		}
		else {
			System.out.print("Din input gav ikke mening, vil du prøve igjen? (Y/N) ");
			String yesNo = scanner.nextLine();
			if(yesNo.equalsIgnoreCase("Y")) {
				main(null);
			}
			else System.exit(0);

		}
		System.out.print("Vil du kryptere eller dekryptere mer? (Y/N) ");
		String more = scanner.nextLine();
		if(more.equalsIgnoreCase("Y")) {
			main(null);
		}
		else System.exit(0);
		scanner.close();
	}

	/**
	 * Shifts a letter through the alphabet
	 * Capital letter is shifted to Capital letter 
	 * and lower case is shifted to lower case. 
	 * @param c - an character in the English alphabet, 
	 * either capital or lower case letter
	 * 
	 * @param steps - number of steps to shift characters
	 * @return char - shifted the given steps
	 * 
	 */
	public static char shiftCharacter(char c, int steps){
		if(steps == 0) {
			return c;
		}
		return (char)(nextIndex(c, steps));
	}
	
	/**
	 * @param index - index in the alphabet (0-25)
	 * @param steps - number of steps to right-shift (positive or negative)
	 * @return an index in the alphabet (0-25) such that it is steps away from the original index.
	 */
	private static int nextIndex(int index, int steps) {
		int asciiLower = 'a';
		int asciiUpper = 'A';
		int finalStep;
		if(index >= 65 && index <= 90) {
			int nextStepUpper = ((index - asciiUpper) + steps) % num_chars;
			if(nextStepUpper < 0) {
				finalStep = nextStepUpper + num_chars + asciiUpper;
			}
			else finalStep = nextStepUpper + asciiUpper;
		}
		else {
			int nextStepLower = ((index - asciiLower) + steps) % num_chars;
			if(nextStepLower < 0) {
				finalStep = nextStepLower + num_chars + asciiLower;
			}
			else finalStep = nextStepLower + asciiLower;
		}
		return finalStep;
	}
	
	/**
	 * @param s - The text to encode
	 * @param steps - Number of steps to shift the letters
	 * @return - the coded text
	 */
	public static String encrypt(String s, int steps) {
		StringBuilder newString = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ' ') {
				newString.append(s.charAt(i));
			}
			else {
				char newChar = shiftCharacter(s.charAt(i), steps);
				newString.append(newChar);
			}
		}
		return newString.toString();
	}
	
	/**
	 * @param s - the coded message
	 * @param steps - the number of steps that was used to code
	 * @return - the decoded message
	 */
	public static String decrypt(String s, int steps){
		return encrypt(s, - steps);
	}

}
