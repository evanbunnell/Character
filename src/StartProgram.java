import java.util.List;
import java.util.Scanner;

import controller.CharacterBuilderHelper;
import model.CharacterBuilder;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static CharacterBuilderHelper cbh = new CharacterBuilderHelper();

		private static void addAChar() {
			System.out.print("Enter a name: ");
			String name = in.nextLine();
			System.out.print("Enter a species: ");
			String species = in.nextLine();
			System.out.print("Enter a job: ");
			String job = in.nextLine();
			CharacterBuilder toAdd = new CharacterBuilder(name, job, species);
			cbh.insertCharacter(toAdd);
		}

		private static void deleteAChar() {
			System.out.print("Enter the name to delete: ");
			String name = in.nextLine();
			System.out.print("Enter the species to delete: ");
			String species = in.nextLine();
			System.out.print("Enter the job to delete: ");
			String job = in.nextLine();
			CharacterBuilder toDelete = new CharacterBuilder(name, job, species);
			cbh.deleteCharacter(toDelete);
		}

		private static void editAChar() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by name");
			System.out.println("2 : Search by job");
			System.out.println("3 : Search by species");
			int searchBy = in.nextInt();
			in.nextLine();
			List<CharacterBuilder> foundChar;
			if (searchBy == 1) {
				System.out.print("Enter the character's name: ");
				String charName = in.nextLine();
				foundChar = cbh.searchForCharacterByName(charName);
				
			} else if (searchBy == 2){
				System.out.print("Enter the job: ");
				String charJob = in.nextLine();
				foundChar = cbh.searchForCharacterByJob(charJob);
			} else {
				System.out.print("Enter the species: ");
				String jobName = in.nextLine();
				foundChar = cbh.searchForCharacterByJob(jobName);
			}

			if (!foundChar.isEmpty()) {
				System.out.println("Found Results.");
				for (CharacterBuilder l : foundChar) {
					System.out.println(l.getId() + " : " + l.returnCharacterDetails());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				CharacterBuilder toEdit = cbh.searchForCharacterById(idToEdit);
				System.out.println("Retrieved " + (toEdit.returnCharacterDetails()));
				System.out.println("1 : Update name");
				System.out.println("2 : Update job");
				System.out.println("3 : Update species");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} else if (update == 2) {
					System.out.print("New job: ");
					String newJob = in.nextLine();
					toEdit.setJob(newJob);
				} else if (update == 3) {
					System.out.print("New species: ");
					String newSpecies = in.nextLine();
					toEdit.setSpecies(newSpecies);
				}

				cbh.updateCharacter(toEdit);

			} else {
				System.out.println("---- No results found");
			}
		}

		public static void main(String[] args) {
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println(".:. Character Organizer .:.");
			while (goAgain) {
				System.out.println("*  Select a task:");
				System.out.println("*  1 -- Add a character");
				System.out.println("*  2 -- Edit a character");
				System.out.println("*  3 -- Delete a character");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAChar();
				} else if (selection == 2) {
					editAChar();
				} else if (selection == 3) {
					deleteAChar();
				} else if (selection == 4) {
					viewTheList();
				} else {
					cbh.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			List<CharacterBuilder> alljobs = cbh.showAllCharacters();
			for(CharacterBuilder singlejob : alljobs) {
				System.out.println(singlejob.returnCharacterDetails());
			}

		}

	}