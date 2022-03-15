---
layout: page
title: User Guide
---

HustleBook (HB) is a **desktop app for managing client details and meetings, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HB can get your client management meetings done faster than traditional GUI apps.

* Table of Contents
  * [Quick Start](#quick-start)
  * [Features](#features)
    * [Adding a client : `add`](#adding-a-client--add)
    * [Listing all persons : `list`](#listing-all-persons--list)
    * [Flagging a person : `flag`](#flagging-a-person--flag)
    * [Editing a person : `edit`](#editing-a-person--edit)
    * [Locating persons by name : `find`](#locating-persons-by-name--find)
    * [Deleting a person : `delete`](#deleting-a-person--delete)
    * [Clearing all entries : `clear`](#clearing-all-entries--clear)
    * [Exiting the program : `exit`](#exiting-the-program--exit)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
  * [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `hustlebook.jar` from [here](https://github.com/AY2122S2-CS2103T-W15-2/tp/releases/).

1. Copy the file to the folder you want to use as the _home folder_ for your HustleBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the HustleBook.

   * **`delete`**`John Doe` : Deletes `John Doe` from the list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `cleardemo`, `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a client : `add`

Adds a client to the HustleBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [f/FLAG] [i/INFO] [d/DATE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client's data can be added in the future through edit command
</div>

* `d/DATE` will be set to today's date by default if not specified.
  * `DATE` has to be in the format **YYYY-MM-DD**.
* `i/INFO` will be set to `No further info` by default if not specified.
* `t/TAG` will be empty by default if not specified.
* `f/FLAG` will be set to `false` by default if not specified.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/Blk 775 Pasir Ris Street 71 S510775`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/NUS School of Computing, COM1 p/1234567 i/Salary of $3400 f/true`

### Listing all persons : `list`

Shows a list of all clients in the HustleBook.

Format: `list`

### Sorting all persons : `sort`

Displays all clients in the HustleBook according to previous date met. 
Flagged clients are displayed before unflaggeed clients.

Format: `sort`

### Flagging a person : `flag`

Flag a person in the HustleBook to mark them as important. Can also be used to unflag the person.

Format: `flag INDEX f/FLAG`

* `FLAG` input should either be true or false.
* `True` or `False` is not case-sensitive.

### Editing a person : `edit`

Edits an existing person in the HustleBook.

Format: `edit NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/INFO] [d/DATE] [t/TAG]…​`

* Edits the person named `NAME`.
  * `Name` is case-insensitive. E.g. `John` will match `john`.
  * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
  * Only full words will be matched e.g. `Han` will not match `Hans`.
* `DATE` has to be in the format **YYYY-MM-DD**.
* **At least one** of the optional fields must be provided.
* Existing values will be updated with the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* You can remove the person's info by typing `i/` without specifying any info after it.

Example:
* `edit n/John Doe d/2020-12-04` Edits the meeting date of the person with the name `John Doe` to `2020-12-04` which is 4th Dec 2020.

### Locating persons by name : `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>


  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the HustleBook.

Format: `delete NAME`

* Deletes the person with the specified `NAME`.
    * `Name` is case-insensitive. e.g. `John` will match `john`.
    * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
    * Only full words will be matched e.g. `Han` will not match `Hans`.
* In the event of multiple clients found with the same `NAME`, the first occurrence of the client in the list will be deleted.
  
Example: 
* `delete John` deletes the person named `John` in the HustleBook.

### Clearing all entries : `clear`

Clears all entries from the HustleBook.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HustleBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HustleBook data are saved as a JSON file `[JAR file location]/data/hustlebook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HustleBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                 |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [d/DATE] [i/INFO] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 d/Salary-3400` |
| **List**   | `list`            
| **Sort**   | `sort`            |
| **Clear**  | `clear`                                                                                                                                                                          |
| **Delete** | `delete NAME`<br> e.g., `delete John`                                                                                                                                              |
| **Flag**   | `flag INDEX f/FLAG`<br> e.g., `flag 3 f/true`                                                                                                                                    |
| **Edit**   | `edit NAME [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DATE] [i/INFO] [t/TAG]…​`<br> e.g.,`edit John n/James Lee e/jameslee@example.com`                                    |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                       |
| **Help**   | `help`                                                                                                                                                                           |
