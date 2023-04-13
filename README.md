# Genealogical Tree App

## Description

This is a simple console-based app that reads the data for a genealogical tree from a csv file and allows the user to
search for the relation between two people in the tree.

## Requirements

The program was developed using Java 20. It may work with earlier versions of Java, but this has not been tested.

## Installation

To install the program, simply clone the repository to your local machine using the following command:

```bash
git clone
```

## Usage

First, compile the program using the following command:

```bash
javac -d bin src/*.java
```

Then, run the program using the following command:

```bash
java -cp bin GenealogicalTreeApp
```

When run, a menu with the available options will be displayed. The user can then select an option by entering the
corresponding number and pressing `Enter`.

The available options are as follows:

- **Read the file and save the data to the tree:** <p>The user will be prompted to enter the path to the file from which
  the data will be read. The program will then read the data from the file and save it to the tree. A message will be
  displayed to indicate whether the data was successfully read and saved.

- **Save the people in the tree to a file in alphabetical order:** <p>The user will be prompted to enter the name of the
  file to which the data will be saved. The program will then save the data to the file. A message will be displayed to
  indicate whether the data was successfully saved.

- **Search for a relation between two people:** <p>The user will be prompted to enter the names of the two people. The
  program will then search for the relation between the two people and display the result.

- **Exit the program:** <p>The program will exit.

