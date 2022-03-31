package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Info;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PrevDateMet;
import seedu.address.model.person.Salary;
import seedu.address.model.person.ScheduledMeeting;
import seedu.address.model.tag.Tag;

/**
 * Flags a person identified using it's displayed index from the hustle book.
 */
public class FlagCommand extends Command {

    public static final String FLAG_COMMAND_WORD = "flag";
    public static final String UNFLAG_COMMAND_WORD = "unflag";

    public static final String MESSAGE_USAGE = FLAG_COMMAND_WORD + "|" + UNFLAG_COMMAND_WORD
            + ": Flags the person identified by the index number used in the displayed person list.\n"
            + "Parameters: NAME\n"
            + "Example: " + UNFLAG_COMMAND_WORD + " Joe";

    public static final String MESSAGE_FLAG_PERSON_SUCCESS = "Updated flag for Person: %1$s";

    private final Name targetName;
    private final Flag flag;

    /**
     * @param targetName Name of person whose flag to replace.
     * @param flag Flag to replace with.
     */
    public FlagCommand(Name targetName, Flag flag) {
        this.targetName = targetName;
        this.flag = flag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        FilteredList<Person> lastShownList = (FilteredList<Person>) model.getFilteredPersonList();
        Index targetIndex = model.getPersonListIndex(targetName);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToFlag = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = createFlagEditedPerson(personToFlag, flag);
        model.setPerson(personToFlag, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_FLAG_PERSON_SUCCESS, personToFlag));
    }

    /**
     * Creates and returns a {@code Person} with only the details of {@code flag}
     * edited.
     */
    private static Person createFlagEditedPerson(Person personToEdit, Flag flag) {
        assert personToEdit != null;

        Name personName = personToEdit.getName();
        Phone personPhone = personToEdit.getPhone();
        Email personEmail = personToEdit.getEmail();
        Address personAddress = personToEdit.getAddress();
        Flag updatedFlag = flag;
        Set<Tag> personTags = personToEdit.getTags();
        Salary personSalary = personToEdit.getSalary();
        Info personInfo = personToEdit.getInfo();
        PrevDateMet personPrevDateMet = personToEdit.getPrevDateMet();

        // Schedule meeting remains unchanged through edit commands. MeetCommand handles CRUD for meetings.
        ScheduledMeeting personScheduledMeeting = personToEdit.getScheduledMeeting();

        return new Person(personName, personPhone, personEmail, personAddress, updatedFlag,
                personTags, personPrevDateMet, personSalary, personInfo, personScheduledMeeting);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlagCommand // instanceof handles nulls
                && targetName.equals(((FlagCommand) other).targetName)); // state check
    }
}
