package seedu.clinic.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinic.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.clinic.testutil.Assert.assertThrows;
import static seedu.clinic.testutil.TypicalIndexes.INDEX_FIRST_SUPPLIER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

// import seedu.clinic.logic.commands.AddCommand;
import seedu.clinic.logic.commands.ClearCommand;
import seedu.clinic.logic.commands.DeleteCommand;
// import seedu.clinic.logic.commands.EditCommand;
// import seedu.clinic.logic.commands.EditCommand.EditSupplierDescriptor;
import seedu.clinic.logic.commands.ExitCommand;
import seedu.clinic.logic.commands.FindCommand;
import seedu.clinic.logic.commands.HelpCommand;
import seedu.clinic.logic.commands.ListCommand;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.supplier.SupplierProductsContainKeywordsPredicate;
// import seedu.clinic.model.supplier.Supplier;
// import seedu.clinic.testutil.EditSupplierDescriptorBuilder;
// import seedu.clinic.testutil.SupplierBuilder;
// import seedu.clinic.testutil.SupplierUtil;

public class ClinicParserTest {

    private final ClinicParser parser = new ClinicParser();

    /*
    @Test
    public void parseCommand_add() throws Exception {
        Supplier supplier = new SupplierBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(SupplierUtil.getAddCommand(supplier));
        assertEquals(new AddCommand(supplier), command);
    }
     */

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_SUPPLIER.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_SUPPLIER), command);
    }

    /*
    @Test
    public void parseCommand_edit() throws Exception {
        Supplier supplier = new SupplierBuilder().build();
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder(supplier).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_SUPPLIER.getOneBased() + " " + SupplierUtil.getEditSupplierDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_SUPPLIER, descriptor), command);
    }
     */

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findSuppliers() throws Exception {
        List<String> keywords = Arrays.asList("supplier", "panadol");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new SupplierProductsContainKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}