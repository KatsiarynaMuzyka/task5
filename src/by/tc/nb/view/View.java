package by.tc.nb.view;

import java.io.IOException;
import java.util.Scanner;

import by.tc.nb.bean.AddNoteRequest;
import by.tc.nb.bean.FindNotesRequest;
import by.tc.nb.bean.FindNotesResponse;
import by.tc.nb.bean.NewNoteBookRequest;
import by.tc.nb.bean.RegistrationRequest;
import by.tc.nb.bean.LoginationRequest;
import by.tc.nb.bean.LoginationResponce;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.entity.User;
import by.tc.nb.bean.ShowRequest;
import by.tc.nb.bean.ShowResponse;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.controller.Controller;
import by.tc.nb.service.exception.ServiceException;

public class View {

	public static void main(String[] args) throws ClassNotFoundException, ServiceException, IOException {
		Controller controller = new Controller();
		int id = 0;
		Scanner in = new Scanner(System.in);

		String menu = ("Menu\n" + "[1] - Add new note\n" + "[2] - Find notes in the notebook by content\n"
				+ "[3] - Find notes in the notebook by date\n" + "[4] - Show notes in the notebook\n"
				+ "[5] - Create new NoteBook\n" + "[0] - Exit");

		boolean a = true;
		boolean b = true;
		while (a = true) {
			System.out.println("Press 1- Authorization");
			System.out.println("Press 2- Registration");
			System.out.println("Press 0- Exit");
			in = new Scanner(System.in);
			int choice1 = in.nextInt();

			switch (choice1) {
			case 0:
				a = false;
				System.out.println("Exit..");
				continue;

			case 1:
				LoginationRequest request = new LoginationRequest();
				System.out.println("Login: ");
				in = new Scanner(System.in);
				String login = in.nextLine();
				System.out.println("Password: ");
				String password = in.nextLine();
				request.setCommandName("LOGINATION");
				request.setPassword(password);
				request.setLogin(login);
				LoginationResponce response = (LoginationResponce) controller.doRequest(request);
				if (response.isErrorStatus() == false) {
					User currentUser = response.getUser();
					id = currentUser.getId();
					System.out.println("Entered as, " + currentUser.getLogin());

					while (b == true) {
						System.out.println(menu);

						int choice = in.nextInt();

						switch (choice) {
						case 0:
							System.out.println("Exit");
							b = false;
							break;

						case 1:
							AddNoteRequest addNoteRequest = new AddNoteRequest();
							addNoteRequest.setCommandName("ADD_NEW_NOTE");
							addNoteRequest.setId(id);
							System.out.println("Enter your note");

							in = new Scanner(System.in);
							String note = in.nextLine();
							addNoteRequest.setNote(note);
							System.out.println("Enter your note");
							Response addNoteResponce = controller.doRequest(addNoteRequest);
							if (response.isErrorStatus() == true) {
								System.out.println(addNoteResponce.getErrorMessage());
							} else {

								System.out.println("Note was added");

							}
							break;

						case 2:
							FindNotesRequest findNotesRequest = new FindNotesRequest();
							findNotesRequest.setCommandName("FIND_NOTES_BY_CONTENT");
							findNotesRequest.setId(id);
							System.out.println("Enter your note");
							in = new Scanner(System.in);
							String sNote = in.nextLine();
							findNotesRequest.setNote(sNote);

							FindNotesResponse findNoteResponse = (FindNotesResponse) controller
									.doRequest(findNotesRequest);

							if (findNoteResponse.getFindBook().size() == 0) {
								System.out.println("There are no notes!");
							} else {
								for (Note n : findNoteResponse.getFindBook()) {
									System.out.println(n.getDate() + " " + n.getNote());
								}
							}
							if (findNoteResponse.isErrorStatus() == true) {
								System.out.println(findNoteResponse.getErrorMessage());
							}
							break;

						case 3:
							FindNotesRequest findNotesRequestByDate = new FindNotesRequest();
							findNotesRequestByDate.setCommandName("FIND_NOTES_BY_DATE");
							findNotesRequestByDate.setId(id);
							System.out.println("Enter your date");
							in = new Scanner(System.in);
							String date = in.nextLine();
							findNotesRequestByDate.setDate(date);

							FindNotesResponse findNoteResponseByDate = (FindNotesResponse) controller
									.doRequest(findNotesRequestByDate);
							if (findNoteResponseByDate.getFindBook().size() == 0) {
								System.out.println("No notes!");
							} else {
								for (Note n : findNoteResponseByDate.getFindBook()) {
									System.out.println(n.getDate() + " " + n.getNote());
								}
							}
							if (findNoteResponseByDate.isErrorStatus() == true) {
								System.out.println(findNoteResponseByDate.getErrorMessage());
							}
							break;

						case 4:
							ShowRequest showRequest = new ShowRequest();
							showRequest.setCommandName("SHOW_NOTES");
							showRequest.setId(id);
							ShowResponse showResponse = (ShowResponse) controller.doRequest(showRequest);
							if (showResponse.isErrorStatus() == true) {
								System.out.println(showResponse.getErrorMessage());
							} else {
								for (Note nt : showResponse.getNotes()) {
									System.out.println(nt.getDate() + ' ' + nt.getNote());
								}
							}
							break;

						case 5: {
							NewNoteBookRequest newNoteBookRequest = new NewNoteBookRequest();
							newNoteBookRequest.setCommandName("NEW_NOTEBOOK");
							newNoteBookRequest.setId(id);
							Response newNoteBookResponse = controller.doRequest(newNoteBookRequest);
							if (newNoteBookResponse.isErrorStatus() == false) {
								System.out.println(newNoteBookResponse.getResultMessage());
							} else {
								System.out.println(newNoteBookResponse.getErrorMessage());
							}
						}
							break;

						default:
							System.out.println("Incorrect symbol");
							break;
						}
					}

				} else {
					System.out.println("Incorrect username or password.");
					continue;
				}
				break;

			case 2:
				RegistrationRequest registrationRequest = new RegistrationRequest();
				registrationRequest.setCommandName("REGISTRATION");
				System.out.println("Login: ");
				in = new Scanner(System.in);
				registrationRequest.setLogin(in.nextLine());
				System.out.println("Password: ");
				in = new Scanner(System.in);
				registrationRequest.setPassword(in.nextLine());
				Response registrationResponce = controller.doRequest(registrationRequest);
				if (registrationResponce.isErrorStatus() == true) {
					System.out.println(registrationResponce.getErrorMessage());
				} else {
					System.out.println("OK");
				}
				continue;
			default:
				System.out.println("Error!");
				continue;
			}
			break;
		}

	}
}
