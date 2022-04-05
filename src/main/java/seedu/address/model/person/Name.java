package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the hustle book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX) && isFirstLetterCapitalised(test);
    }

    /**
     * Validity check to ensure first letter is capitalised
     * @param test The name to be tested.
     * @return true if first letter for each name is capitalised.
     */
    private static boolean isFirstLetterCapitalised(String test) {
        String[] name = test.split(" ");
        return checkEachName(name);
    }

    /**
     * Method helper to check for validity of name
     * @param name An array containing each part of the name.
     * @return True if name is valid.
     */
    private static boolean checkEachName(String[] name) {
        for (int i = 0; i < name.length; i++) {
            String word = name[i];
            if (!Character.isUpperCase(word.charAt(0)) && !Character.isDigit(word.charAt(0))) {
                return false;
            }
            for (int j = 1; j < word.length(); j++) {
                if (!Character.isLowerCase(word.charAt(j)) && !Character.isDigit(word.charAt(0))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int compare(Name otherName) {
        return this.fullName.compareToIgnoreCase(otherName.fullName);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
